package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.sendTest.HttpUtil;
import top.mnilsy.cup.utils.MD5Util;
import top.mnilsy.cup.utils.ResponMessage;

import java.util.Map;


/**
 * Created by mnilsy on 19-5-9 下午2:16.
 */
public class Test {
    public static void main(String[] args) {
        /*//46F9938AEAEFE24A9B6EBF044362B682
        ResponMessage responMessage = HttpUtil.
//                passwdLogin("MNILSY", "123abc?");
//        getPhoneCode("13536497415",null);
//        codeLogin("13536497415","424362","FEBF7DB2FB4CFCBC7AD7490630454215");
//        logout("FEBF7DB2FB4CFCBC7AD7490630454215");
        register("13536497415","660359","0894FF8C207760FB74ECA2C6DD1F37CB");
        System.out.println(JSON.toJSONString(responMessage));*/
        String password = "123abc!?";
        String password2 = MD5Util.MD5Encode(password,"utf8");
        String password3 = MD5Util.convertMD5(password);
        System.out.println("密码："+password);
        System.out.println("编码1密码："+password2);
        System.out.println("编码2密码："+password3);
    }
}
