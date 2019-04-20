package top.mnilsy.cup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojoOV.UserPojoVO;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupApplicationTests {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        UserPojoVO userPojoOV=userMapper.getUserInfo("325e29e4-de68-4a71-9ad6-781dce8d4159");
        System.out.println(userPojoOV.toString());
    }

}
