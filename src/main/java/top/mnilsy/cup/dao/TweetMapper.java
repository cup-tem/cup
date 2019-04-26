package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.TweetVO;

/**
 * Created by mnilsy on 19-4-25 下午1:18.
 */
@Repository("tweetMapper")
public interface TweetMapper {
    /**
     * 获取一条可显示推文
     *
     * @param tweet_Id 推文id
     * @return
     */
    @Select("")
    TweetVO getTweetVO(String tweet_Id);

    /**
     * 根据推文id获取发推文用户的id
     *
     * @param tweet_Id 推文id
     * @return
     */
    @Select("")
    String getUserId(String tweet_Id);


    /**
     * 根据推文id获取发推文用户名
     *
     * @param tweet_Id 推文id
     * @return
     */
    @Select("")
    String getUserName(String tweet_Id);
}
