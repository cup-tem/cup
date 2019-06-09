package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.*;
import top.mnilsy.cup.sendTest.HttpUtil;
import top.mnilsy.cup.service.MessageService;
import top.mnilsy.cup.utils.ResponMessage;
import top.mnilsy.cup.utils.SendSMSUtil;
import top.mnilsy.cup.utils.SpringUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupApplicationTests {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "discussMapper")
    private DiscussMapper discussMapper;

    @Resource(name = "accessoryMapper")
    private AccessoryMapper accessoryMapper;

    @Resource(name = "tweetMapper")
    private TweetMapper tweetMapper;

    @Resource(name = "blacklistMapper")
    private BlacklistMapper blacklistMapper;

    @Test
    public void contextLoads() {
        UserVO userVO = new UserVO("mnilsy", "ShuyL", "男", "13536497415", "mnilsy.top@gmail.com", "大头像", "小头像", "背景", "二维码", "sesionid");
        UserVO userV1 = new UserVO("emmmmm", "ShuyLv", "男", "15876566484", "mnilsy.top@gmail.com", "大头像", "小头像", "背景", "二维码", "sesionid");
        List<Object> list = new ArrayList<>();
        list.add(userVO);
        list.add(userV1);
        ResponMessage responMessage = new ResponMessage(200, "TestMessage", list);
        System.out.println(JSON.toJSONString(responMessage));
    }

    @Test
    public void ptr(){
        System.out.println(userMapper.selectUser("MNILSY").toString());
    }

    @Test
    public void Testsms(){
        System.out.println(JSON.toJSONString(HttpUtil.passwdLogin("MNILSY","123abc?")));
    }

}
