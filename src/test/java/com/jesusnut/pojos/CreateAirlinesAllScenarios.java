package com.jesusnut.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({
        "id",
        "name",
        "country",
        "logo",
        "slogan",
        "head_quaters",
        "website",
        "established"
})
public class CreateAirlinesAllScenarios extends BasePojo {


    private Integer id;
    private String name;
    private String country;
    private String logo;
    private String slogan;
    @JsonProperty("head_quaters")
    private String headQuaters;
    private String website;
    private String established;


}
