package com.springboot.refresher.converter;

import com.springboot.refresher.dto.GenderDTO;
import com.springboot.refresher.entity.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenderConverter {

    public Gender dtoToEntity(GenderDTO genderDTO) {
        Gender gender = new Gender();
        gender.setGenderCode(genderDTO.getGenderCode());
        gender.setGenderLabel(genderDTO.getGenderLabel());
        return gender;
    }

    public GenderDTO entityToDTO(Gender gender) {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setGenderCode(gender.getGenderCode());
        genderDTO.setGenderLabel(gender.getGenderLabel());
        return genderDTO;
    }

    public Page<Gender> dtoToEntity(Page<GenderDTO> genderDTOPage) {
        return new PageImpl<>(genderDTOPage.stream().map(this::dtoToEntity).collect(Collectors.toList()));
    }

    public Page<GenderDTO> entityToDTO(Page<Gender> genderPage) {
        return new PageImpl<>(genderPage.stream().map(this::entityToDTO).collect(Collectors.toList()));
    }
}
