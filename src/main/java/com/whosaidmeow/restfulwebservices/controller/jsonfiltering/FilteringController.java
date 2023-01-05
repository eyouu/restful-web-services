package com.whosaidmeow.restfulwebservices.controller.jsonfiltering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.whosaidmeow.restfulwebservices.model.jsonfiltering.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filteringJsonProperty() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return formFilter(someBean, "property1", "property2");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringJsonPropertyList() {
        List<SomeBean> someBeans = asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));

        return formFilter(someBeans, "property3");
    }

    private <T> MappingJacksonValue formFilter(T value, String... propertiesNotToFilter) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertiesNotToFilter);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }
}
