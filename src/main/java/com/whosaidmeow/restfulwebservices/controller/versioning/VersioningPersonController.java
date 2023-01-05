package com.whosaidmeow.restfulwebservices.controller.versioning;

import com.whosaidmeow.restfulwebservices.model.versioning.Name;
import com.whosaidmeow.restfulwebservices.model.versioning.PersonV1;
import com.whosaidmeow.restfulwebservices.model.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    // **********************************************
    // URI Versioning Approach
    // **********************************************

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Joe Doe");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Joe", "Doe"));
    }

    // **********************************************
    // Request Parameter Versioning
    // **********************************************

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonByParameter() {
        return new PersonV1("Joe Doe");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonByParameter() {
        return new PersonV2(new Name("Joe", "Doe"));
    }

    // **********************************************
    // Headers Versioning
    // **********************************************

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonByHeader() {
        return new PersonV1("Joe Doe");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonByHeader() {
        return new PersonV2(new Name("Joe", "Doe"));
    }

    // **********************************************
    // Media Type Versioning
    // **********************************************

    @GetMapping(path = "/person/header", produces = "application/vnd.company.all-v1+json")
    public PersonV1 getFirstVersionOfPersonByMediaType() {
        return new PersonV1("Joe Doe");
    }

    @GetMapping(path = "/person/header", produces = "application/vnd.company.all-v2+json")
    public PersonV2 getSecondVersionOfPersonByMediaType() {
        return new PersonV2(new Name("Joe", "Doe"));
    }
}
