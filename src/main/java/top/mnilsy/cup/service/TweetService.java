package top.mnilsy.cup.service;

import com.alibaba.fastjson.JSONArray;
import top.mnilsy.cup.VO.DiscussVO;
import top.mnilsy.cup.VO.TweetVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnilsy on 19-4-25 下午1:12.
 */
public interface TweetService {
    /**
     * 获取推文
     *
     * @param tweet_Id 推文id
     * @return 推文的VO包
     * @author mnilsy
     */
    TweetVO getTweet(String tweet_Id);

    /**
     * 获取用户关注的人的推文
     *
     * @param user_Id 用户id
     * @param count 获取次数，每次10条
     * @return 推文的VO包
     * @author mnilsy
     */
    List<TweetVO> getFollowTweet(String user_Id,int count);

    /**
     * 获取制定推文的10条评论
     *
     * @param tweet_Id 推文id
     * @param count 获取评论的次数
     * @return 评论的VO包
     * @author mnilsy
     */
    List<DiscussVO> getTweetDiscuss(String tweet_Id,int count);

    /**
     * 发布一条推文，并即时通知被@的人
     *
     * @param tweet_Type  推文的类型
     * @param tweet_Text  推文的文字
     * @param accessory   推文的附件
     * @param atUser_Name 被@的用户名
     * @param user_Id     发布者的用户id
     * @return 否是发布成功
     * @author mnilsy
     */
    boolean addTweet(int tweet_Type, String tweet_Text, ArrayList accessory, ArrayList atUser_Name, String user_Id);

    /**
     * 点赞/取消赞
     *
     * @param tweet_Id 推文id
     * @param user_Id  用户id
     * @return 是否点赞/取消赞成功
     * @author mnilsy
     */
    boolean putLike(String tweet_Id, String user_Id);

    /**
     * 删除推文
     *
     * @param tweet_Id 推文id
     * @param user_Id  推文发布者id
     * @return 是否删除成功
     * @author mnilsy
     */
    boolean deleteTweet(String tweet_Id, String user_Id);

    /**
     * 删除评论
     *
     * @param discuss_Id 评论id
     * @param user_Id    评论者id
     * @return 是否删除成功
     * @author mnilsy
     */
    boolean deleteDiscuss(String discuss_Id, String user_Id);

    /**
     * 删除评论
     *
     * @param writeBack_Id 回复id
     * @param user_Id      回复者id
     * @return 是否删除成功
     * @author mnilsy
     */
    boolean deleteWriteback(String writeBack_Id, String user_Id);

    /**
     * 发表评论
     *
     * @param tweet_Id     推文id
     * @param user_Id      评论者id
     * @param discuss_Vlue 评论内容
     * @return 是否发表成功
     * @author mnilsy
     */
    boolean putDiscuss(String tweet_Id, String user_Id, String discuss_Vlue);


    /**
     * 发表评论
     *
     * @param discuss_Id        评论id
     * @param user_Id           回复者id
     * @param writeBack_User_Name 回复的用户名
     * @param writeBack_Vlue    回复内容
     * @return 是否回复成功
     * @author mnilsy
     */
    boolean putWriteback(String discuss_Id, String user_Id,String writeBack_User_Name, String writeBack_Vlue);
}
