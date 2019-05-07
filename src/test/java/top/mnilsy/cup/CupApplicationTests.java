package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.*;
import top.mnilsy.cup.pojo.AccessoryPojo;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.utils.ResponMessage;
import top.mnilsy.cup.utils.SendSMSUtil;

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
        UserVO userVO =new UserVO("mnilsy","ShuyL","男","13536497415","mnilsy.top@gmail.com","大头像","小头像","背景","二维码","sesionid");
        UserVO userV1 =new UserVO("emmmmm","ShuyLv","男","15876566484","mnilsy.top@gmail.com","大头像","小头像","背景","二维码","sesionid");
        List<Object> list=new ArrayList<>();
        list.add(userVO);
        list.add(userV1);
        ResponMessage responMessage=new ResponMessage(200,"TestMessage", list);
        System.out.println(JSON.toJSONString(responMessage));
    }

    @Test
    public void sendsms(){
        System.out.println(SendSMSUtil.send("15876566484","579900"));
    }

    @Test
    @Transactional
    public void addtest(){
        AccessoryPojo[] accessoryPojos= new AccessoryPojo[3];
        for (int i=0;i<accessoryPojos.length;i++){
            accessoryPojos[i]=new AccessoryPojo("tweet"+i,"url"+i);
        }
        System.out.println(accessoryMapper.insetAccessory(accessoryPojos));

        accessoryPojos= new AccessoryPojo[3];
        accessoryPojos[2]=new AccessoryPojo(null,null);
        for (int i=0;i<accessoryPojos.length-1;i++){
            accessoryPojos[i]=new AccessoryPojo("tweet"+i,"url"+i);
        }
        System.out.println(accessoryMapper.insetAccessory(accessoryPojos));
    }

    @Test
    public void creUUid(){
        System.out.println(UUID.randomUUID().toString());
    }
    @Test
    public void listtoString(){
        System.out.println();
    }

    @Test
    public void testMapper(){
        UserPojo userPojo = userMapper.getUserByUserName("MNILSY");
        System.out.println(userPojo);
    }

    @Test
    public void testController(){

    }
}
