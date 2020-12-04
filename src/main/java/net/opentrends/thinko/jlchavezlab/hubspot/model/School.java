package net.opentrends.thinko.jlchavezlab.hubspot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class School {

    private String country;
    private String name;
    private String state;
    private String domain;
    private String updatedOn;
    private String createdOn;
    private Id id;
}

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class Id {
    private String id;
}