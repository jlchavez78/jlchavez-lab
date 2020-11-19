package net.opentrends.thinko.jlchavezlab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordsTest {

    @Test
    public void test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<String> usuaris = new ArrayList<>();
        usuaris.add("encarna.badenes@gmail.com;Encarna;Badenes;null");
        for (String user : usuaris) {
            String[] full = user.split(";");
            String username = full[0];
            String name = full[1];
            String surname = full[2];
            String lastname = full[3];
            String[] partes = username.split("@");
            String password = partes[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String data = sdf.format(Calendar.getInstance().getTime());

            String encodedPassword = passwordEncoder.encode(password);
            System.out.println("INSERT INTO `users`(`created_at`, `updated_at`, `active`, `email`, "
                    + "`last_name`, `link_date`, `linkurl`, `mail_sended`, "
                    + "`name`, `newsletter_subscription`, `password`, `profile`, "
                    + "`recovery_date`, `recovery_link`, `sur_name`, `usertype`, `zipcode`, `profile_completed`,`beta_tester`) VALUES (" + "'" + data
                    + "','" + data + "','1','" + username + "','" + lastname + "',NULL,NULL,'1','" + name + "','0','"
                    + encodedPassword + "','TEACHER'," + "NULL,NULL,'" + surname + "',NULL,NULL,1,1);");
        }

    }
}