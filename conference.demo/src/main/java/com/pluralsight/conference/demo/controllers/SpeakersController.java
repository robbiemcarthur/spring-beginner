package com.pluralsight.conference.demo.controllers;

import com.pluralsight.conference.demo.models.Speaker;
import com.pluralsight.conference.demo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    private final SpeakerRepository speakerRepository;

    public SpeakersController(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @GetMapping
    public List<Speaker> getList() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @DeleteMapping
    public void deleteById(@RequestBody final Speaker speaker) {
        speakerRepository.delete(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody final Speaker speaker) {
        // TODO: Validation
        Speaker existingSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
