package net.opentrends.thinko.jlchavezlab;

import net.opentrends.thinko.jlchavezlab.hubspot.feign.IFeignHubspotClient;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Contact;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Parameters;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactResponseTest {

    @Autowired
    IFeignHubspotClient client;

    @Test
    public void testGetLead() {
        Parameters p = new Parameters();
        p.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        p.setProperties("firstname,lastname,phone,cargoenelcentro,city,email,etapa");
        long lead = 65230701L;
        Contact c = client.getContact(lead, p);
        System.out.println(c.toString());
    }

    //    @Test
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