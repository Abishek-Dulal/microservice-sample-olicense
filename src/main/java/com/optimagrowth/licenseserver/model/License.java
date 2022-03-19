package com.optimagrowth.licenseserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author abishek on 2022-03-05
 */
@Getter
@Setter
@Table(schema = "license_server",name="licenses")
@ToString
public class License extends RepresentationModel<License> {
    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;
    private String description;
    @Column(name="organization_id", nullable = false)
    private String organisationId;
    @Column(name="product_name",nullable = false)
    private String productName;
    @Column(name="licenseType")
    private String licenseType;
    @Column(name="comment")
    private String comment;

    public License withComment(String comment){
         this.setComment(comment);
         return this;
    }

}
