package net.opentrends.thinko.jlchavezlab;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillTemplatesApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class MandrillTest {

    private static final String key = "_zmFlZSsNCRJYNEH2fL8Tw";
    private static final String from = "info@thinkoeducation.com";


    @Test
    public void testMail() throws IOException, MandrillApiError {
        MandrillApi mandrillApi = new MandrillApi(key);
        MandrillTemplatesApi templatesApi = new MandrillTemplatesApi(key);
        MandrillMessage message = new MandrillMessage();

        message.setSubject("¡Tienes una invitación para crear colaborativamente con Thinkö!");
        message.setHtml(templatesApi.publish("activation_pending").getCode().replace("#thinko/activation_link_sent",
                "http://www.google.es"));

        message.setAutoText(true);
        message.setFromEmail(from);
        message.setFromName("Thinkö");
        // add recipients
        ArrayList<MandrillMessage.Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
        MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
        recipient.setEmail("maria.llambi@thinkoeducation.com");
        recipients.add(recipient);
        message.setTo(recipients);
        message.setPreserveRecipients(true);
        mandrillApi.messages().send(message, false);
    }
}
