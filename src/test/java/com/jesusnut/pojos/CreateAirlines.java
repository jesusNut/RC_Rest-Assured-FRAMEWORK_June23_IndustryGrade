
package com.jesusnut.pojos;

import com.fasterxml.jackson.annotation.*;
import com.jesusnut.enums.RandomDataTypeNames;
import com.jesusnut.utils.DateTimeUtils;
import com.jesusnut.utils.RandomDataGenerator;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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

@ExcelSheet("createAirline")
public class CreateAirlines {

    @JsonProperty("id")
    private Integer id = RandomDataGenerator.getRandomNumber(5);


    // Only usage of this property is to map String 'id' data coming from excel.
    // Value of this property is
    // a. fetched as String ; using getter method 'getIdStringValue()'; from excel
    // b. transformed to Integer and set in 'id' property as : setId(Integer.parseInt(modifyIdValAsPerRules(idValInStringFromExcel)))
    // Used in com.jesusnut.payloads.createAirlinePayloads#getCreateAirlinePayload_POJO_DP_POIJI
    @JsonIgnore
    @ExcelCellName("id")
    private String idStringValue;

    @JsonProperty("name")
    @ExcelCellName("name")
    private String name = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME)+" Airlines";
    @JsonProperty("country")
    @ExcelCellName("country")
    private String country = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRY);
    @JsonProperty("logo")
    @ExcelCellName("logo")
    private String logo = RandomDataGenerator.getRandomAlphabets(22);
    @JsonProperty("slogan")
    @ExcelCellName("slogan")
    private String slogan = RandomDataGenerator.getRandomAlphabets(20);
    @JsonProperty("head_quaters")
    @ExcelCellName("headquaters")
    private String headQuaters = RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.CITYNAME);
    @JsonProperty("website")
    @ExcelCellName("website")
    private String website = RandomDataGenerator.getRandomWebsiteName();
    @JsonProperty("established")
    @ExcelCellName("established")
    private String established = String.valueOf(RandomDataGenerator.getRandomNumberInRange(1900, DateTimeUtils.getCurrentYear()));

    @JsonIgnore
    @ExcelCellName("expectedStatusCode")
    private String expectedStatusCode;

}
