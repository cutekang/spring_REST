package com.app.restful.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PetTourDTO {
    private String address;
    private String dong;
    private String areaCode;
    private String contentId;
    private String title;
    private String firstImage1;
    private String firstImage2;
    private String tel;
    private String zipCode;
}
