package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.DiscussVO;
import top.mnilsy.cup.VO.Discuss_AtVO;
import top.mnilsy.cup.pojo.DiscussPojo;

import java.util.List;

/**
 * Created by mnilsy on 19-4-27 下午10:15.
 */
@Repository("discussMapper")
@Mapper
public interface DiscussMapper {
    /**
     * 根据评论id获取评论atVO包
     *
     * @param discuss_Id 评论id
     * @return 评论atVO包
     * @author mnilsy
     */
    @Select("select user.user_HeadUrl_min," +
            "user.user_Name," +
            "user.user_NickName," +
            "discuss.discuss_Time," +
            "discuss.discuss_Vlue," +
            "discuss.discuss_Id," +
            "tweet.tweet_Id," +
            "tweet.tweet_Text" +
            "from discuss" +
            "join user on discuss.user_Id = user.user_Id" +
            "join tweet on discuss.tweet_Id = tweet.tweet_Id")
    Discuss_AtVO getDiscuss_AtVO(String discuss_Id);

    /**
     * 根据评论id获推文发布用户id
     *
     * @param discuss_Id 评论id
     * @return 评论者id
     * @author mnilsy
     */
    @Select("select user.user_Id" +
            "from user join tweet on user.user_Id = tweet.user_Id" +
            "join discuss on tweet.tweet_Id = discuss.tweet_Id" +
            "where discuss.discuss_Id=#{discuss_Id}")
    String getTweetUserId(String discuss_Id);

    /**
     * 更改评论记录的状态discuss_Condition为1
     *
     * @param discuss_Id 评论id
     * @param user_Id    评论者id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update discuss set discuss_Condition=1 where discuss_Id=#{discuss_Id} and user_Id=#{user_Id}")
    int updateCondition(@Param("discuss_Id") String discuss_Id, @Param("user_Id") String user_Id);

    /**
     * 增加一条评论记录
     *
     * @param discussPojo 评论的pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("insert into discuss (discuss_Id, tweet_Id, user_Id, discuss_Vlue) values (#{discuss_Id},#{tweet_Id},#{user_Id},#{discuss_Vlue})")
    int insetrDiscuss(DiscussPojo discussPojo);

    /**
     * 获取指定推文10条评论
     *
     * @param tweet_Id 评论id
     * @param count    获取次数
     * @return 评论VO包
     * @author mnilsy
     */
    @Select("select u.user_HeadUrl_min,u.user_Name,u.user_NickName,d.discuss_Vlue,d.discuss_Id " +
            "from user u join discuss d on u.user_Id = d.user_Id where d.tweet_Id=#{tweet_Id} and d.discuss_Condition=0 limit #{count},10")
    List<DiscussVO> getTweetDiscuss(@Param("tweet_Id") String tweet_Id, @Param("count") int count);
}
