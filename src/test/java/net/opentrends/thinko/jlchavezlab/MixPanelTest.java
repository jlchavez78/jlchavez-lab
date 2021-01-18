package net.opentrends.thinko.jlchavezlab;

import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MixPanelTest {

    @Test
    public void testEvent(){
        try {
            MessageBuilder messageBuilder = new MessageBuilder("db561c7eaaa9e7b40687f2bd7e85a1ed");
            JSONObject props = new JSONObject();
            props.put("environment", "prd");
            String mail = "juanluis.chavez78@gmail.com";
            JSONObject playEvent = messageBuilder.event(mail, "open_guia", props);
            ClientDelivery delivery = new ClientDelivery();
            Map<String, Object> namePropsMap = new HashMap<>();
            namePropsMap.put("$distinct_id", mail);
            String name = "Juan Luis Chavez";
                namePropsMap.put("$first_name", name);
            namePropsMap.put("$email", mail);
            namePropsMap.put("$team", true);
            namePropsMap.put("$betaTester", false);
            JSONObject nameProps = new JSONObject(namePropsMap);
            JSONObject nameMessage = messageBuilder.set(mail, nameProps);
            delivery.addMessage(nameMessage);
            delivery.addMessage(playEvent);

            MixpanelAPI mixpanel = new MixpanelAPI();
            //mixpanel.deliver(delivery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


