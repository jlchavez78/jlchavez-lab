package net.opentrends.thinko.jlchavezlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.opentrends.thinko.jlchavezlab.hubspot.feign.IFeignHubspotClient;
import net.opentrends.thinko.jlchavezlab.hubspot.model.*;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Properties;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactResponseTest {

    @Autowired
    IFeignHubspotClient client;

    /**
     * Get lead by ID, but you can use search
     */
    //@Test
    public void testGetLead() {
        Parameters p = new Parameters();
        p.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        p.setProperties("firstname,lastname,phone,cargoenelcentro,city,email,etapa");
        long lead = 65230701L;
        Contact c = client.getContact(lead, p);
        System.out.println(c.toString());
    }

    @Test
    public void searchLead() {
        Parameters p = new Parameters();
        p.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        SearchPayload pl = new SearchPayload();
        pl.setAfter(0);
        pl.setLimit(0);
        pl.setProperties(Arrays.asList("email","firstname"));
        pl.setSorts(Arrays.asList("email"));
        FilterGroups fg = new FilterGroups();
        Filter f = new Filter();
        f.setOperator("EQ");
        f.setValue("jlchavez@opentrends.net");
        f.setPropertyName("email");
        fg.setFilters(Arrays.asList(f));
        pl.setFilterGroups(Arrays.asList(fg));
        Object o = client.searchContact(pl, p);
        System.out.println(o);
    }


    /**
     * Save lead
     */
    //@Test
    public void testSetLead() {
        long lead = 65230701L;
        Parameters param = new Parameters();
        param.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        Properties p = new Properties();
        p.setFirstname("Juan Luis");
        p.setEtapa("Primaria");
        Map<String, Object> fields = new HashMap<>();
        fields.put("properties", p);
        Contact res = client.update(lead, fields, param);
        System.out.println(res.toString());
    }
}