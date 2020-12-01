package net.opentrends.thinko.jlchavezlab.hubspot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import feign.template.Literal;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchPayload {

    private List<FilterGroups> filterGroups;
    private List<String> sorts;
    private List<String> properties;
    private Integer limit;
    private Integer after;
}
