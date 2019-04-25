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
     */
    TweetVO getTweet(String tweet_Id);
}
