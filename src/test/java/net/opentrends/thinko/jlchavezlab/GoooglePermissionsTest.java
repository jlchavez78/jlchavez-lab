package net.opentrends.thinko.jlchavezlab;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoooglePermissionsTest {

    private static final String token = "ya29.a0AfH6SMDxkK1p0fqrpXFArBUUOBl5p3CNL9f_zUtKAi1cpv3CBkcRS_0t28c0vqgquGj4mBTDFZPSc1deU2hE21JbqvR68M1v25gV9_UjHiipA0NSPey-HveJvN2A8_O7MkBCdY-n1dz67jqTtqZaY_687WTlv4wornxnrgvIzC3acQ";

    @Test
    public void testPermissions() throws GeneralSecurityException, IOException {
        Drive drive = this.cloneGoogleFile();
        BatchRequest batch = drive.batch();
        Permission userPermission = new Permission().setType("anyone").setRole("reader");
        drive.permissions().insert("10OdAdMC1gK_Z-T-HwrwPgDwJzeMAMdcPbnwj_8ayUCQ", userPermission).execute();
        drive.permissions().insert("1LE4qAKm1vGzpOJpI0P5wylW15q68K2Cw_dW6i31ePuI", userPermission).execute();
        drive.permissions().insert("1XY4LvhM5uAO2EIUOEj6W0pbRFCspFUVffiIDhB3VCRA", userPermission).execute();
        drive.permissions().insert("1ifTl1Lk169vpzc036DjWns8yzkCJrxGtjUJNNplqoLQ", userPermission).execute();
        drive.permissions().insert("1VWNARGGzlIlS8l5eJaMoVWVSylMuLaQAhoZS8XY5q48", userPermission).execute();
        drive.permissions().insert("1MGBZRqnpcml6fKr3we5hXjbsNepbfeu8qXAOUh-fChM", userPermission).execute();
        drive.permissions().insert("1YdPRIG8tcz7QDtTQh6d9-f57ULeKdg2GU81bT3FO6Lk", userPermission).execute();
    }


    private Drive cloneGoogleFile() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        GoogleCredential credential = new GoogleCredential();
        credential.setAccessToken(token);
        Drive drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName("").build();
        return drive;
    }
}
