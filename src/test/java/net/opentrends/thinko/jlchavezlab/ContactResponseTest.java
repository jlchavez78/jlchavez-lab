package net.opentrends.thinko.jlchavezlab;

import net.opentrends.thinko.jlchavezlab.hubspot.feign.IFeignHubspotClient;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Parameters;
import net.opentrends.thinko.jlchavezlab.hubspot.model.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactResponseTest {

    @Autowired
    private IFeignHubspotClient client;

    private static final String key = "9ed620b5-b271-4668-a35e-cdbc757accd0";
    private final static String PREMIOS_CAMPAIGN = "premiosabp_feb21";

    private final static String CONECTA_CV_1_CAMPAIGN = "thinko_webinar_conecta_1";
    private final static String CONECTA_CV_2_CAMPAIGN = "thinko_webinar_conecta_2";
    private final static String CONECTA_CV_3_CAMPAIGN = "thinko_webinar_conecta_3";


    private final static String EVENT_NAME = "profile_activation";
    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static String HOUR_FORMAT = "hh:mm:ss";

    @Test
    public void test() {
        sendToHubspot("premiosabp_feb21",0L,LocalDateTime.now());

    }

    public void sendToHubspot(String campaign, Long count, LocalDateTime datetime) {
        Parameters params = new Parameters();
        params.setHapikey(key);
        Properties p = addCampaignProperties(campaign, count);
        p.setFirstname("Juan Luis");
        p.setPhone("687788990");
        p.setCity("Barcelona");
        p.setEmail("juanluis.chavez78@gmail.com");
//        p.setCargo_en_el_centro(""user.getRole()"");
        p.setThinko_registro("true");
        p.setThinko_registro_fecha(datetime.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        p.setThinko_registro_hora(datetime.format(DateTimeFormatter.ofPattern(HOUR_FORMAT)));
        Map<String, Object> fields = new HashMap<>();
        fields.put("properties", p);
        client.addContact(fields, params);
    }

    public void updateHubspot(Long id, Long count, String campaign) {
        Parameters params = new Parameters();
        params.setHapikey(key);
        Properties p = addCampaignProperties(campaign, count);
        Map<String, Object> fields = new HashMap<>();
        fields.put("properties", p);
        client.update(id, fields, params);
    }


    private Properties addCampaignProperties(String campaign, Long count) {
        Properties p = new Properties();
        if (campaign.equalsIgnoreCase(PREMIOS_CAMPAIGN)) {
            p.setThinko_grupo_de_control(PREMIOS_CAMPAIGN);
            p.setThinko_premiosabp_feb21_membergetmember(String.valueOf(count));
        } else if (campaign.equalsIgnoreCase(CONECTA_CV_1_CAMPAIGN)) {
            p.setThinko_grupo_de_control(CONECTA_CV_1_CAMPAIGN);
            p.setThinko_webinar_conecta_1_membergetmember_9mar21(String.valueOf(count));
        } else if (campaign.equalsIgnoreCase(CONECTA_CV_2_CAMPAIGN)) {
            p.setThinko_grupo_de_control(CONECTA_CV_2_CAMPAIGN);
            p.setThinko_webinar_conecta_1_membergetmember_16mar21(String.valueOf(count));
        } else if (campaign.equalsIgnoreCase(CONECTA_CV_3_CAMPAIGN)) {
            p.setThinko_grupo_de_control(CONECTA_CV_3_CAMPAIGN);
            p.setThinko_webinar_conecta_1_membergetmember_23mar21(String.valueOf(count));
        }
        return p;
    }


}
