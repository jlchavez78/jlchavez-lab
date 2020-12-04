package net.opentrends.thinko.jlchavezlab;

import net.opentrends.thinko.jlchavezlab.hubspot.feign.IFeignHubspotClient;
import net.opentrends.thinko.jlchavezlab.hubspot.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactResponseTest {

    @Autowired
    IFeignHubspotClient client;

    //@Test
    public void testGetLead() {
        Parameters p = new Parameters();
        p.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        p.setProperties("firstname,lastname,phone,cargoenelcentro,city,email,etapa");
        long lead = 65230701L;
        Contact c = client.getContact(lead, p);
        System.out.println(c.toString());
    }

//    @Test
    public void searchLead() {
        Parameters p = new Parameters();
        p.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        SearchPayload pl = new SearchPayload();
        pl.setAfter(0);
        pl.setLimit(0);
        pl.setProperties(Arrays.asList("email", "firstname"));
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


    //@Test
    public void testSetLead() {
        long lead = 65230701L;
        Parameters param = new Parameters();
        param.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        Properties p = new Properties();
        p.setFirstname("Juan Luis");
//        p.setEtapa("Primaria");
        Map<String, Object> fields = new HashMap<>();
        fields.put("properties", p);
        Contact res = client.update(lead, fields, param);
        System.out.println(res.toString());
    }

    @Test
    public void saveHubspotuser() {
        String id = "";
        Parameters params = new Parameters();
        params.setHapikey("9ed620b5-b271-4668-a35e-cdbc757accd0");
        Contact contact = null;
        Properties p = new Properties();
        p.setCity("Barcelona");
        p.setEmail("jlchavez@opentrends3.net");
        p.setFirstname("Chavez");
        p.setLastname("Lobato");
        p.setPhone("687575756");
//        p.setEtapa("Primaria");
//        p.setThinko_que_asignaturas_impartes_(Arrays.asList("Biología y Geología", "Lengua Castellana y Literatura"));
        p.setThinko_que_asignaturas_impartes_("Biología y Geología;Física y Química;Geografía e Historia");
        Map<String, Object> fields = new HashMap<>();
        fields.put("properties", p);

        if (!id.isBlank()) {
            contact = client.update(Long.getLong(id), fields, params);
        } else {
            contact = client.addContact(fields, params);
        }
        System.out.println(contact);
    }
}