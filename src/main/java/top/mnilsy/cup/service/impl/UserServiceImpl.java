package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import top.mnilsy.cup.pojoOV.UserPojoVO;
import top.mnilsy.cup.service.UserService;

/**
 * Created by mnilsy on 19-4-20 下午7:28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public String getPhoneCode(String user_Phone) {
        System.out.println("输出一个验证码");
        return "TestCode";
    }

    @Override
    public UserPojoVO codeLogin() {
        return null;
    }
}
