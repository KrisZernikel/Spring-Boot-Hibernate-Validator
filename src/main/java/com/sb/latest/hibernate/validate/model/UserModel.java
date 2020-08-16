package com.sb.latest.hibernate.validate.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "first_name", "last_name" })
public class UserModel {

    @NotNull
    @Pattern(regexp = "^[A-Za-z]{1,30}$")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]{1,30}$")
    @JsonProperty("last_name")
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}