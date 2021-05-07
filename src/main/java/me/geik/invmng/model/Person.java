package me.geik.invmng.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Person extends BaseEntity{

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
}
