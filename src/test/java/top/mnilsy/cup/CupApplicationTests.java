package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.util.TweetType;

import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupApplicationTests {

    @Test
    public void contextLoads() {
        two to=new two(8,9);
        One o=new One("this's a","this's b",to);
        String s= JSON.toJSONString(o);
        System.out.println(s);
        Map<String,Object> map=JSON.parseObject("{\"a\":\"this's a\",\"b\":\"this's b\",\"t\":{\"c\":8,\"d\":9}}");
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(((Map)map.get("t")).get("c"));
        System.out.println(map.size());
    }

}
