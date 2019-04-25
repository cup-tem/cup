package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

/**
 * Created by mnilsy on 19-4-21 下午12:22.
 */

/**
 * 附近的人控制器
 */
@RestController
public class NearbyContrller {

    /**
     * 获取定位
     *
     * @param requestMessage 定位经度 data.get("location_Y")，定位纬度 data.get("location_X")
     * @return 请求状态码status
     */
    @PostMapping("/setUserLocation.api")
    public ResponMessage setUserLocation(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 清除定位
     *
     * @param requestMessage
     * @return 请求状态码status
     */
    @GetMapping("/clearUserLocation.api")
    public ResponMessage clearUserLocation(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 获取附近的人的最新推文
     *
     * @param requestMessage 加载次数data.get("count")
     * @return 请求状态码status，推文内容data.tweetPojoVO[]
     */
    @GetMapping("/getLocationTweet.api")
    public ResponMessage getLocationTweet(RequestMessage requestMessage) {
        return new ResponMessage();
    }
}
