package com.whosaidmeow.restfulwebservices.model.jsonfiltering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@JsonIgnoreProperties("property2")
@JsonFilter("SomeBeanFilter")
@AllArgsConstructor
public class SomeBean {

    private String property1;
    private String property2;

//    @JsonIgnore
    private String property3;
}
