package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.sendTest.HttpUtil;
import top.mnilsy.cup.utils.ResponMessage;

import java.util.Map;

/**
 * Created by mnilsy on 19-5-9 下午2:16.
 */
public class Test {
    public static void main(String[] args) {
        ResponMessage responMessage = HttpUtil.passwdLogin("MNILSY", "123456");
        System.out.println(JSON.toJSONString(responMessage));
        System.out.println("status：" + responMessage.getStatus());
        System.out.println("message：" + responMessage.getMessage());
` `        System.out.println("datauserVO：" + ((UserVO)((Map)responMessage.getData()).get("userVO")).getUser_Name());
        System.out.println("datasessionid：" + ((Map)responMessage.getData()).get("sessionid"));
    }
}
