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
public class ContentsDesglosed {

    @Test
    public void test() {
        long maxC = 65885l;
        long maxE = 22135l;
        long minC = 65823l;
        long minE = 22107l;

        for (long i = minC; i<=maxC; i++){
            for (long j = minE; j<=maxE; j++){
                String sql = "INSERT INTO `content_ev_criteria`(`content_id`, `evaluation_criteria_id`) VALUES (" + i + "," + j + ");";
                System.out.println(sql);
            }
        }
    }
}