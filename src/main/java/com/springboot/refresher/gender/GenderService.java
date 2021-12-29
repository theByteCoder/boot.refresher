package com.springboot.refresher.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<Gender> getGenders() {
        return genderRepository.findAll();
    }

    public Gender getGenderByCode(Long code) {
        Optional<Gender> g = genderRepository.findById(code);
        return g.get();
    }

    public HttpStatus createGender(Gender gender) {
        genderRepository.save(gender);
        return HttpStatus.CREATED;
    }
}
