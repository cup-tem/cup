package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
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
     */
    @Insert("insert into tweet(tweet_Id,tweet_Type,tweet_Text,user_Id) values (#{tweetPojo.tweet_Id},#{tweetPojo.tweet_Type},#{tweetPojo.tweet_Text},#{tweetPojo.user_Id})")
    int insertTweet(TweetPojo tweetPojo);
}
