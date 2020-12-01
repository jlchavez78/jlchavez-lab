package net.opentrends.thinko.jlchavezlab.hubspot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter {

    private String value;
    private String propertyName;
    private String operator;
}
