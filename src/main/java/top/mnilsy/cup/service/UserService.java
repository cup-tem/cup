package top.mnilsy.cup.service;

import org.springframework.stereotype.Service;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojoOV.UserPojoVO;

import javax.annotation.Resource;

/**
 * Created by mnilsy on 19-4-20 下午7:15.
 */
public interface UserService {
    /**
     * 获取验证码
     *
     * @param user_Phone 用户手机号码
     * @return 返回是否获取成功
     */
    String getPhoneCode(String user_Phone);

    UserPojoVO codeLogin();
}
