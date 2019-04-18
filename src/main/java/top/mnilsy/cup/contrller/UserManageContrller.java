package top.mnilsy.cup.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mnilsy.cup.util.RequestMessage;
import top.mnilsy.cup.util.ResponMessage;

/**
 * Created by mnilsy on 19-4-18 下午11:59.
 * 用户管理控制器
 */

@Controller
@ResponseBody
public class UserManageContrller {

    /**
     * 用户请求手机验证码
     * @param requestMessage data.
     * @return
     */
    @GetMapping("/getPhoneCode.api")
    public ResponMessage getPhoneCode(@RequestBody RequestMessage requestMessage){
        return new ResponMessage();
    }
}
