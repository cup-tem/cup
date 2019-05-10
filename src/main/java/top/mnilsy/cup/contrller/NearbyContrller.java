package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.NearbyService;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by mnilsy on 19-4-21 下午12:22.
 */

/**
 * 附近的人控制器
 */
@RestController
public class NearbyContrller {

    @Resource(name = "NearbyService")
    private NearbyService nearbyService;

    /**
     * 上传定位
     *
     * @param requestMessage 定位经度 data.get("location_Y")，定位纬度 data.get("location_X")
     * @return 请求状态码status，失败信息 message
     * @author mnilsy
     */
    @PostMapping("/setUserLocation.api")
    public ResponMessage setUserLocation(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        double x = Double.parseDouble((String) requestMessage.getData().get("location_X"));
        double y = Double.parseDouble((String) requestMessage.getData().get("location_Y"));
        boolean flag = nearbyService.location(userPojo.getUser_Id(), x, y);
        return flag ? ResponMessage.ok() : ResponMessage.error("定位失败");
    }

    /**
     * 清除定位
     *
     * @return 请求状态码status，失败信息 message
     * @author mnilsy
     */
    @GetMapping("/clearUserLocation.api")
    public ResponMessage clearUserLocation(HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        boolean flag = nearbyService.cleanLocation(userPojo.getUser_Id());
        return flag ? ResponMessage.ok() : ResponMessage.error("清除失败");
    }

    /**
     * 获取附近的人的最新推文
     *
     * @param requestMessage 获取次数data.get("count")
     * @return 请求状态码status，失败信息 message，推文内容data.List<tweetVO>
     * @author mnilsy
     */
    @GetMapping("/getLocationTweet.api")
    public ResponMessage getLocationTweet(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        int count = Integer.parseInt((String) requestMessage.getData().get("count"));
        List<TweetVO> list = nearbyService.getLocationTweet(userPojo.getUser_Id(), count);
        boolean flag = !list.isEmpty();
        return flag ? ResponMessage.ok(list) : ResponMessage.error("获取失败");
    }
}
