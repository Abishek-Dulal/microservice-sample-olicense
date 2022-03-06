package com.optimagrowth.licenseserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author abishek on 2022-03-05
 */
@Getter
@Setter
@ToString
public class License extends RepresentationModel<License> {
    private int id;
    private String licenseId;
    private String description;
    private String organisationId;
    private String productName;
    private String licenseType;
}
