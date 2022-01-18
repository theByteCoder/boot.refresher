package com.springboot.refresher.controller;

import com.springboot.refresher.entity.Gender;
import com.springboot.refresher.service.GenderService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/genders")
public class GenderController {

    private final CacheControl cacheControl = CacheControl
            .maxAge(60, TimeUnit.MINUTES)
            .noTransform()
            .mustRevalidate();

    @Autowired
    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping
    public ResponseEntity<Page<Gender>> getAllGenders(
            @RequestParam @NonNull int page,
            @RequestParam @NonNull int size
    ) {
        return ResponseEntity.ok().body(genderService.getGenders(page, size));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Gender> getGender(@PathVariable long id) {
        Optional<Gender> gender = genderService.getGenderByCode(id);
        if (gender.isPresent())
            return ResponseEntity.ok().cacheControl(cacheControl).body(gender.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/")
    public HttpStatus createGender(@RequestBody Gender gender) {
        return genderService.createGender(gender);
    }
}
