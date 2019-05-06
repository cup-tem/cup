package top.mnilsy.cup.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mnilsy on 19-5-6 下午11:37.
 */
@Controller
public class TestContrller {
    @RequestMapping("/open/index.html")
    public String index(){
        return "test";
    }
}
