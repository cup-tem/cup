package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.DiscussMapper;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.utils.ResponMessage;
import top.mnilsy.cup.utils.SendSMSUtil;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupApplicationTests {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "discussMapper")
    private DiscussMapper discussMapper;

    @Test
    public void contextLoads() {
        UserVO userVO =new UserVO("mnilsy","ShuyL","男","13536497415","mnilsy.top@gmail.com","大头像","小头像","背景","二维码","sesionid");
        ResponMessage responMessage=new ResponMessage(200,"TestMessage", userVO);
        System.out.println(JSON.toJSONString(responMessage));
    }

    @Test
    public void sendsms(){
        System.out.println(SendSMSUtil.send("15876566484","579900"));
    }


}
