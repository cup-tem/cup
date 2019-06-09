package top.mnilsy.cup;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.security.MD5Encoder;
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
        System.out.println(MD5Util.MD5Encode("123456","utf8"));

    }
}
