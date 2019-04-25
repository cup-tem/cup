package top.mnilsy.cup.contrller;

import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

/**
 * Created by mnilsy on 19-4-21 上午12:43.
 * 推文控制器
 */

@RestController
public class TweetContrller {

    /**
     * 发布推文
     *
     * @param requestMessage 推文类型data.get("tweet_Type")，推文文字data.get("tweet_Text")，
     *                       推文附件data.get("accessory")[]，@的用户data.get("user_Name")[]
     * @return 请求状态码 status
     */
    @PostMapping("/putTweet.api")
    public ResponMessage putTweet(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 查看推文，即点开推文
     *
     * @param requestMapping 推文iddata.get("tweet_Id")
     * @return 请求状态码status，失败信息message，推文data.tweetVO，推文评论discussVO
     */
    @GetMapping("/getTweet.api")
    public ResponMessage getTweet(RequestMapping requestMapping) {
        return new ResponMessage();
    }

    /**
     * 评论推文
     *
     * @param requestMessage 评论内容data.get("discuss_Vlue")
     * @param tweet_Id       推文的id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/putDiscuss{tweet_Id}.api")
    public ResponMessage putDiscuss(RequestMessage requestMessage, @PathVariable String tweet_Id) {
        return new ResponMessage();
    }

    /**
     * 回复评论
     *
     * @param requestMessage 回复内容data.get("write_Vlue")
     * @param discuss_Id     评论的id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/putWriteBack{discuss_Id}.api")
    public ResponMessage putWriteBack(RequestMessage requestMessage, @PathVariable String discuss_Id) {
        return new ResponMessage();
    }

    /**
     * 点赞
     *
     * @param requestMessage
     * @param tweet_Id       推文id
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/putLike{tweet_Id}.api")
    public ResponMessage putLike(RequestMessage requestMessage, @PathVariable String tweet_Id) {
        return new ResponMessage();
    }

    /**
     * 删除推文
     *
     * @param requestMessage
     * @param tweet_Id       推文id
     * @return 请求状态码status
     */
    @PostMapping("/deleteTweet{tweet_Id}.api")
    public ResponMessage deleteTweet(RequestMessage requestMessage, @PathVariable String tweet_Id) {
        return new ResponMessage();
    }

    /**
     * 删除评论
     *
     * @param requestMessage
     * @param discuss_Id     评论的id
     * @return 请求状态码status
     */
    @PostMapping("/deleteDiscuss{discuss_Id}.api")
    public ResponMessage deleteDiscuss(RequestMessage requestMessage, @PathVariable String discuss_Id) {
        return new ResponMessage();
    }

    /**
     * 删除评论回复
     *
     * @param requestMessage
     * @param writeBack_Id   评论回复的id
     * @return 请求状态码status
     */
    @PostMapping("/deleteWriteBack{writeBack_Id}.api")
    public ResponMessage deleteWriteBack(RequestMessage requestMessage, @PathVariable String writeBack_Id) {
        return new ResponMessage();
    }


}
