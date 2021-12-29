package com.springboot.refresher.gender;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/genders")
public class GenderController {

    @Autowired
    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping(value = "/")
    public ResponseEntity getAllGenders() {
        return ResponseEntity.ok().body(genderService.getGenders());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getGender(@PathVariable long id) {
        return ResponseEntity.ok().body(genderService.getGenderByCode(id));
    }

    @PostMapping(value = "/")
    public HttpStatus createGender(@RequestBody Gender gender) {
        return genderService.createGender(gender);
    }
}
