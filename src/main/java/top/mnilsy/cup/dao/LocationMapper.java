package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.pojo.LocationPojo;

import java.util.List;

/**
 * Created by mnilsy on 19-5-5 上午11:20.
 */
@Repository("locationMapper")
@Mapper
public interface LocationMapper {

    /**
     * 增加一条定位记录
     *
     * @param locationPojo 定位的pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("insert into location (location_Id, user_Id, location_X, location_Y, newTweetTime) " +
            "(select #{location_Id},#{user_Id},#{location_X},#{location_Y},tweet_Time from tweet " +
            "where user_Id=#{user_Id} order by tweet_Time desc limit 1)")
    int insertLocation(LocationPojo locationPojo);

    /**
     * 更改x、y
     *
     * @param locationPojo 定位的pojo包
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update location" +
            "set location_X = #{location_X}," +
            "location_Y = #{location_Y}," +
            "newTweetTime = (select tweet_Time " +
            "from tweet" +
            "where tweet.user_Id = #{user_Id} " +
            "order by tweet_Time desc " +
            "limit 1) " +
            "where user_Id = #{user_Id}")
    int updateLocation(LocationPojo locationPojo);


    /**
     * 获取一条可显示推文
     *
     * @param user_Id 用户id
     * @param count   获取次数
     * @return 一组推文的VO包
     * @author mnilsy
     */
    @Select("select u.user_HeadUrl_min," +
            "u.user_Name," +
            "u.user_NickName," +
            "t.tweet_Id," +
            "t.tweet_Time," +
            "t.tweet_Text," +
            "(select count(*) from `like` where tweet_Id = ?)  as tweet_LikeCount," +
            "(select count(*) from discuss where tweet_Id = ?) as tweet_DiscussCount " +
            "from user u " +
            "join tweet t on u.user_Id = t.user_Id " +
            "where u.user_Id in " +
            "(select l1.user_Id " +
            "from location l1," +
            "location l2 " +
            "where l1.location_X >= l2.location_X - 0.01 " +
            "and l1.location_X <= l2.location_X + 0.01 " +
            "and l1.location_Y >= l2.location_Y - 0.01 " +
            "and l1.location_Y <= l2.location_Y + 0.01 " +
            "and timestampdiff(day, l1.location_Time, now()) <= 3 " +
            "and l2.user_Id = #{user_Id} " +
            "order by newTweetTime desc " +
            "limit #{count},10)")
    @Results({
            @Result(property = "accessory", column = "tweet_Id",
                    many = @Many(select = "top.mnilsy.cup.dao.AccessoryMapper.getAccessoryUrl")
            )
    })
    List<TweetVO> getTweet(@Param("user_Id") String user_Id, @Param("count") int count);
}
