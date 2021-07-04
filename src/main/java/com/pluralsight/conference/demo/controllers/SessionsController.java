package com.pluralsight.conference.demo.controllers;

import com.pluralsight.conference.demo.models.Session;
import com.pluralsight.conference.demo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    private final SessionRepository sessionRepository;

    public SessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Session>> list()
    {
        return ResponseEntity.ok(sessionRepository.findAll());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Session> get(@PathVariable Long id){
        Optional<Session> session = sessionRepository.findById(id);

        if(!session.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Session with %1$s not found", id));

        return ResponseEntity.ok(sessionRepository.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody final Session session, UriComponentsBuilder uriComponentsBuilder){
        sessionRepository.saveAndFlush(session);

        UriComponents uriComponents = uriComponentsBuilder.path("api/v1/sessions/{id}").buildAndExpand(session.getSession_id());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody final Session session) {
        Session existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
