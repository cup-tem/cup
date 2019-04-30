package top.mnilsy.cup.service;

import top.mnilsy.cup.VO.TweetVO;

/**
 * Created by mnilsy on 19-4-25 下午1:12.
 */
public interface TweetService {
    /**
     * 获取推文
     *
     * @param tweet_Id 推文id
     * @return
     * @author mnilsy
     */
    TweetVO getTweet(String tweet_Id);

    /**
     * 发布一条推文，并即时通知被@的人
     * @param tweet_Type 推文的类型
     * @param tweet_Text 推文的文字
     * @param accessory 推文的附件
     * @param atUser_Name 被@的用户名
     * @param user_Id 发布者的用户id
     * @return 否是发布成功
     */
    boolean addTweet(int tweet_Type,String tweet_Text,String[] accessory,String [] atUser_Name,String user_Id);

    /**
     * 点赞/取消赞
     * @param tweet_Id 推文id
     * @param user_Id 用户id
     * @return 是否点赞/取消赞成功
     */
    boolean putLike(String tweet_Id,String user_Id);
}
