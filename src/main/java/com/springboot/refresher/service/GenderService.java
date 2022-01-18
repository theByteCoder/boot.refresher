package com.springboot.refresher.service;

import com.springboot.refresher.entity.Gender;
import com.springboot.refresher.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public Page<Gender> getGenders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return genderRepository.findAll(pageable);
    }

    public Optional<Gender> getGenderByGenderCode(Long genderCode) {
        return genderRepository.findByGenderCode(genderCode);
    }

    public HttpStatus createGender(Gender gender) {
        Optional<Gender> genderOptional = genderRepository.findByGenderCode(gender.getGenderCode());
        if (genderOptional.isPresent()) return HttpStatus.CONFLICT;
        genderRepository.save(gender);
        return HttpStatus.CREATED;
    }
}
