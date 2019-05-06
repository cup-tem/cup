package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.Like_AtVO;
import top.mnilsy.cup.pojo.LikePojo;

/**
 * Created by mnilsy on 19-4-28 上午1:33.
 */
@Repository("likeMapper")
@Mapper
public interface LikeMapper {
    /**
     * 根据赞的id获取赞的atVO包
     *
     * @param like_Id 赞的id
     * @return 赞的atVO包
     * @author mnilsy
     */
    @Select("select u.user_HeadUrl_min, u.user_Name, u.user_NickName, l.like_Time, t.tweet_Id, t.tweet_Text " +
            "from user u " +
            "join `like` l on u.user_Id = l.user_Id " +
            "join tweet t on l.tweet_Id = t.tweet_Id " +
            "where l.like_Id = #{like_Id}")
    Like_AtVO getLike_AtVO(String like_Id);

    /**
     * 根据点赞id获取推文发布用户id
     *
     * @param like_Id 赞id
     * @return 推文发布用户id
     * @author mnilsy
     */
    @Select("select t.user_Id from `like` l join tweet t on l.tweet_Id = t.tweet_Id where l.like_Id=#{like_Id}")
    String getTweetUserId(String like_Id);

    /**
     * 增加一条点赞记录
     *
     * @param likePojo 赞的pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("INSERT INTO `like` (like_Id, tweet_Id, user_Id) " +
            "SELECT #{like_Id},#{tweet_Id},#{user_Id} " +
            "from DUAL " +
            "where not exists(select * from 'like' where tweet_Id=#{tweet_Id} and user_Id=#{user_Id})")
    int insertLike(LikePojo likePojo);

    /**
     * 更改点赞的状态
     *
     * @param tweet_Id 推文id
     * @param user_Id  点赞用户id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update 'like' set like_Condition=(case when like_Condition=0 then 1 else 0 end) " +
            "where tweet_Id=#{tweet_Id} and user_Id=#{user_Id}")
    int updateCondition(@Param("tweet_Id") String tweet_Id, @Param("user_Id") String user_Id);
}
