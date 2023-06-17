package com.jesusnut.pojos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePojo {

//to accomodate extra columns that are present in the excel sheet but are not part of actual POJO (i.e. CreateAirlines here).

    @JsonIgnore
    private String scenarioDesc;

    @JsonIgnore
    private String expectedStatusCode;

    @JsonIgnore
    private String exectedErrorMessage;

}
