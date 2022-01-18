package com.springboot.refresher.controller;

import com.springboot.refresher.converter.GenderConverter;
import com.springboot.refresher.dto.GenderDTO;
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

    private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.MINUTES).noTransform().mustRevalidate();

    private final GenderService genderService;
    private final GenderConverter genderConverter;

    public GenderController(@Autowired GenderService genderService, @Autowired GenderConverter genderConverter) {
        this.genderService = genderService;
        this.genderConverter = genderConverter;
    }

    @GetMapping
    public ResponseEntity<Page<GenderDTO>> getAllGenders(@RequestParam @NonNull int page, @RequestParam @NonNull int size) {
        Page<GenderDTO> genderDTOPage = genderConverter.entityToDTO(genderService.getGenders(page, size));
        return ResponseEntity.ok().body(genderDTOPage);
    }

    @GetMapping(value = "/{genderCode}")
    public ResponseEntity<GenderDTO> getGender(@PathVariable long genderCode) {
        Optional<Gender> gender = genderService.getGenderByGenderCode(genderCode);
        if (gender.isPresent()) {
            GenderDTO genderDTO = genderConverter.entityToDTO(gender.get());
            return ResponseEntity.ok().cacheControl(cacheControl).body(genderDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/")
    public HttpStatus createGender(@RequestBody GenderDTO genderDTO) {
        Gender gender = genderConverter.dtoToEntity(genderDTO);
        return genderService.createGender(gender);
    }
}
