package com.cns.restfulws.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    // url versioning
    @GetMapping("/person/v1")
    public PersonV1 getPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/person/v2")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // param versioning
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getPersonV1Param() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getPersonV2Param() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // header versioning
    @GetMapping(value = "/person", headers = "X-API-Version=1")
    public PersonV1 getPersonV1Header() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", headers = "X-API-Version=2")
    public PersonV2 getPersonV2Header() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // MimeType or Produces versioning
    @GetMapping(value = "/person", produces = "application/vnd.com.cns-v1+json")
    public PersonV1 getPersonV1Produces() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person", produces = "application/vnd.com.cns-v2+json")
    public PersonV2 getPersonV2Produces() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

}
