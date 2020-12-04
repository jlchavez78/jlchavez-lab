package net.opentrends.thinko.jlchavezlab.hubspot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolsPayload {

    private List<School> schools;
}
