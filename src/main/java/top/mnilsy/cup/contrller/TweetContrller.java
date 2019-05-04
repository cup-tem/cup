package top.mnilsy.cup.contrller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.VO.DiscussVO;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.TweetService;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by mnilsy on 19-4-21 上午12:43.
 * 推文控制器
 */

@RestController
public class TweetContrller {

    @Resource(name = "tweetService")
    private TweetService tweetService;

    /**
     * 发布推文
     *
     * @param requestMessage 推文类型data.get("tweet_Type")，推文文字data.get("tweet_Text")，
     *                       推文附件data.get("accessory")[]，@的用户data.get("user_Name")[]
     * @return 请求状态码 status
     * @author mnilsy
     */
    @PostMapping("/putTweet.api")
    public ResponMessage putTweet(RequestMessage requestMessage, HttpSession session) {
        UserPojo userInfo = (UserPojo) session.getAttribute("userInfo");
        int tweet_Type = Integer.parseInt((String) requestMessage.getData().get("tweet_Type"));
        String tweet_Text = (String) requestMessage.getData().get("tweet_Text");
        String accessory[] = (String[]) requestMessage.getData().get("accessory");
        String user_Name[] = (String[]) requestMessage.getData().get("user_Name");
        boolean flag = tweetService.addTweet(tweet_Type, tweet_Text, accessory, user_Name, userInfo.getUser_Id());
        return flag ? ResponMessage.ok() : ResponMessage.error();
    }

    /**
     * 查看推文，即点开推文
     *
     * @param requestMessage 推文id data.get("tweet_Id")
     * @return 请求状态码status，失败信息message，推文data.tweetVO，推文评论data.List<discussVO>
     * @author mnilsy
     */
    @GetMapping("/openTweet.api")
    public ResponMessage openTweet(RequestMessage requestMessage) {
        String tweet_Id = (String) requestMessage.getData().get("tweet_Id");
        TweetVO tweetVO = tweetService.getTweet(tweet_Id);
        List<DiscussVO> discussVOList = tweetService.getTweetDiscuss(tweet_Id, 0);
        if (tweet_Id == null || discussVOList.isEmpty()) return ResponMessage.error("推文不存在");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tweetVO", tweetVO);
        jsonObject.put("discussVO", discussVOList);
        return ResponMessage.ok(jsonObject);
    }

    /**
     * 获取指定推文的更多评论
     *
     * @param requestMessage 推文id data.get("tweet_Id")，获取次数 data.get("count")
     * @return 请求状态码status，失败信息message，推文评论data.List<discussVO>
     * @author mnilsy
     */
    @GetMapping("/getMoreDiscuss.api")
    public ResponMessage getMoreDiscuss(RequestMessage requestMessage) {
        String tweet_Id = (String) requestMessage.getData().get("tweet_Id");
        String count = (String) requestMessage.getData().get("count");
        List<DiscussVO> discussVOList = tweetService.getTweetDiscuss(tweet_Id, Integer.parseInt(count));
        if (discussVOList.isEmpty()) return ResponMessage.error("没有更多评论了");
        return ResponMessage.ok(discussVOList);
    }

    /**
     * 评论推文
     *
     * @param requestMessage 评论内容data.get("discuss_Vlue")
     * @param tweet_Id       推文的id
     * @return 请求状态码status，失败信息message
     * @author mnilsy
     */
    @PostMapping("/putDiscuss{tweet_Id}.api")
    public ResponMessage putDiscuss(RequestMessage requestMessage, @PathVariable String tweet_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        //获取信息
        String discuss_Vlue = (String) requestMessage.getData().get("discuss_Vlue");
        if (discuss_Vlue == null) return ResponMessage.error("评论为空");//过滤空评论
        boolean flag = tweetService.putDiscuss(tweet_Id, userPojo.getUser_Id(), discuss_Vlue);
        return flag ? ResponMessage.ok() : ResponMessage.error("推文不存在");
    }

    /**
     * 回复评论
     *
     * @param requestMessage    回复内容data.get("writeBack_Vlue")，评论的id data.get("discuss_Id")
     * @param writeBack_User_Id 回复的用户名的id
     * @return 请求状态码status，失败信息message
     * @author mnilsy
     */
    @PostMapping("/putWriteBack{writeBack_User_Id}.api")
    public ResponMessage putWriteBack(RequestMessage requestMessage, @PathVariable String writeBack_User_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        //获取信息
        String writeBack_Vlue = (String) requestMessage.getData().get("writeBack_Vlue");
        String discuss_Id = (String) requestMessage.getData().get("discuss_Id");
        if (writeBack_Vlue == null) return ResponMessage.error("回复为空");//过滤空回复
        boolean flag = tweetService.putWriteback(discuss_Id, userPojo.getUser_Id(), writeBack_User_Id, writeBack_Vlue);
        return flag ? ResponMessage.ok() : ResponMessage.error("评论不存在");
    }

    /**
     * 点赞
     *
     * @param tweet_Id 推文id
     * @return 请求状态码status，失败信息message
     * @author mnilsy
     */
    @PostMapping("/putLike{tweet_Id}.api")
    public ResponMessage putLike(@PathVariable String tweet_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        boolean flag = tweetService.putLike(tweet_Id, userPojo.getUser_Id());
        return flag ? ResponMessage.ok() : ResponMessage.error("推文被删除");
    }

    /**
     * 删除推文
     *
     * @param tweet_Id 推文id
     * @return 请求状态码status
     * @author mnilsy
     */
    @PostMapping("/deleteTweet{tweet_Id}.api")
    public ResponMessage deleteTweet(@PathVariable String tweet_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        return tweetService.deleteTweet(tweet_Id, userPojo.getUser_Id()) ? ResponMessage.ok() : ResponMessage.error("非发布用户删除");
    }

    /**
     * 删除评论
     *
     * @param discuss_Id 评论的id
     * @return 请求状态码status
     * @author mnilsy
     */
    @PostMapping("/deleteDiscuss{discuss_Id}.api")
    public ResponMessage deleteDiscuss(@PathVariable String discuss_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        return tweetService.deleteDiscuss(discuss_Id, userPojo.getUser_Id()) ? ResponMessage.ok() : ResponMessage.error("非评论用户删除");

    }

    /**
     * 删除评论回复
     *
     * @param writeBack_Id 评论回复的id
     * @return 请求状态码status
     * @author mnilsy
     */
    @PostMapping("/deleteWriteBack{writeBack_Id}.api")
    public ResponMessage deleteWriteBack(@PathVariable String writeBack_Id, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        return tweetService.deleteTweet(writeBack_Id, userPojo.getUser_Id()) ? ResponMessage.ok() : ResponMessage.error("非回复用户删除");

    }


}
