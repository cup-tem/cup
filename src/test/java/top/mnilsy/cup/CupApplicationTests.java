package top.mnilsy.cup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mnilsy.cup.util.TweetType;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupApplicationTests {

    @Test
    public void contextLoads() {
        String s= String.valueOf(UUID.randomUUID());
        System.out.println(s);
        System.out.println(s.substring(22,s.length()));
    }

}
