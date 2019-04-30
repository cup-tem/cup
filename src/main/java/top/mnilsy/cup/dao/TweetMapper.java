package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.pojo.TweetPojo;

/**
 * Created by mnilsy on 19-4-25 下午1:18.
 */
@Repository("tweetMapper")
@Mapper
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
     * @return 用户id
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

    /**
     * 增加一条推文记录
     *
     * @param tweetPojo 推文pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("insert into tweet(tweet_Id,tweet_Type,tweet_Text,user_Id) values (#{tweetPojo.tweet_Id},#{tweetPojo.tweet_Type},#{tweetPojo.tweet_Text},#{tweetPojo.user_Id})")
    int insertTweet(TweetPojo tweetPojo);

    /**
     * 更改推文记录的状态tweet_Condition
     *
     * @param tweet_Id 推文id
     * @param user_Id  用户id
     * @return 改变的条数
     * @author mnilsy
     */
    @Update("update tweet set tweet_Condition='1' where tweet_Id=#{tweet_Id} and user_Id=#{user_Id}")
    int updateCondition(@Param("tweet_Id") String tweet_Id, @Param("user_Id") String user_Id);
}
