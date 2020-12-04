package net.opentrends.thinko.jlchavezlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.opentrends.thinko.jlchavezlab.hubspot.feign.IFeignHubspotClient;
import net.opentrends.thinko.jlchavezlab.hubspot.feign.IFeignHubspotSchoolsClient;
import net.opentrends.thinko.jlchavezlab.hubspot.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolSearchTest {

    @Autowired
    IFeignHubspotSchoolsClient client;

    @Test
    public void testSearchSchool() throws JsonProcessingException {
        Parameters p = new Parameters();
        p.setSearch_string("Singuer");
        p.setCountry("España");
        p.setState("Cataluña");
        String result = client.getSchools(p);
        System.out.println(result);
        result = result.substring(1);
        result = result.substring(0,result.length()-1);
        System.out.println(result);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        School[] o = objectMapper.readValue(result, School[].class);
//        for(School s:schools){
//            System.out.println(s.getName());
//        }
    }

//    @Test
    public void testDeserialize() throws JsonProcessingException {
        String payload = "[{\"name\":\"Singuerlin (Santa Coloma de Gramenet), Colegio\",\"domain\":\"singuerlin.cat\",\"state\":\"Catalu\\u00f1a\",\"country\":\"Espa\\u00f1a\",\"createdOn\":\"2020-11-13T13:08:41+01:00\",\"updatedOn\":\"2020-11-13T13:08:41+01:00\",\"id\":{\"id\":2290889583}}]";
//        String payload = "[{\"name\":\"Singuerlin (Santa Coloma de Gramenet), Colegio\",\"state\":\"Catalu\\u00f1a\",\"country\":\"Espa\\u00f1a\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        School[] o = objectMapper.readValue(payload, School[].class);
    }

}