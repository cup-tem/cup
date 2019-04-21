package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojoVO.UserPojoVO;
import top.mnilsy.cup.util.ResponMessage;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupApplicationTests {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        UserPojoVO userPojoVO=new UserPojoVO("mnilsy","ShuyL","男","13536497415","mnilsy.top@gmail.com","大头像","小头像","背景","二维码","sesionid");
        ResponMessage responMessage=new ResponMessage(200,"TestMessage",userPojoVO);
        System.out.println(JSON.toJSONString(responMessage));
    }

}
