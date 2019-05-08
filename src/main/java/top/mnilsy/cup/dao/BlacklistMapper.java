package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.UserListVO;
import top.mnilsy.cup.pojo.BlacklistPojo;

import java.util.List;

/**
 * Created by mnilsy on 19-5-2 上午10:56.
 */
@Repository("blacklistMapper")
@Mapper
public interface BlacklistMapper {

    /**
     * 增加黑名单记录
     *
     * @param blacklistPojo 黑名单的pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("INSERT INTO blacklist (blackList_Id, firstParty_User_Id, secondParty_User_Id) " +
            "SELECT #{blackList_Id},#{firstParty_User_Id},#{secondParty_User_Id} " +
            "from DUAL " +
            "where not exists(select * from fans where firstParty_User_Id=#{firstParty_User_Id} and secondParty_User_Id=#{secondParty_User_Id})")
    int insertBlacklist(BlacklistPojo blacklistPojo);

    /**
     * 更改黑名单状态
     *
     * @param firstParty_User_Id  拉黑者id
     * @param secondParty_User_Id 被拉黑者id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update blacklist set blackList_Condition=(case when blackList_Condition=0 then 1 else 0 end) " +
            "where firstParty_User_Id=#{firstParty_User_Id} and secondParty_User_Id=#{secondParty_User_Id}")
    int updateCondition(@Param("firstParty_User_Id") String firstParty_User_Id, @Param("secondParty_User_Id") String secondParty_User_Id);


    /**
     * 根据用户id黑名单的人，每次获取15个
     *
     * @param user_Id 用户id
     * @param count   获取次数
     * @return 用户拉黑的人的VO包
     * @author mnilsy
     */
    @Select("select u.user_HeadUrl_min,u.user_NickName,u.user_Name " +
            "from blacklist b join user u on b.secondParty_User_Id = u.user_Id " +
            "where b.blackList_Condition=0 and u.user_Condition=0 and firstParty_User_Id=#{user_Id} limit #{count},15")
    List<UserListVO> getBlackist(@Param("user_Id") String user_Id, @Param("count") int count);

    /**
     * 根据两用户名查询是否为黑名单关系
     *
     * @param message_Recipient_user_Name 被拉黑者用户名
     * @param message_Recipient_user_Name 拉黑者用户名
     * @return 条数
     * @author mnilsy
     */
    @Select("select count(*) " +
            "from blacklist b " +
            "join user u1 on b.firstParty_User_Id = u1.user_Id " +
            "join user u2 on b.secondParty_User_Id = u2.user_Id " +
            "where u1.user_Name = #{message_Recipient_user_Name} " +
            "and u2.user_Name = #{message_Sender_User_Name}")
    int isBlacklistByuserName(@Param("message_Sender_User_Name") String message_Sender_User_Name, @Param("message_Recipient_user_Name") String message_Recipient_user_Name);

    /**
     * 根据用户id和推文id查询该用户与推文发布者是否为黑名单关系
     *
     * @param user_Id  用户id
     * @param tweet_Id 推文id
     * @return 条数
     * @author mnilsy
     */
    @Select("select count(*) " +
            "from blacklist b " +
            "join tweet t on b.firstParty_User_Id = t.user_Id " +
            "where t.tweet_Id = ? " +
            "and b.secondParty_User_Id = ?")
    int isBlacklistByTweet(@Param("user_Id") String user_Id, @Param("tweet_Id") String tweet_Id);

    /**
     * 根据用户id查询与回复评论者、评论者、推文发布者是否为黑名单关系
     *
     * @param user_Id             用户id
     * @param writeBack_User_Name 评论者用户名
     * @param discuss_Id          评论id
     * @return 条数
     * @author mnilsy
     */
    @Select("select count(*) " +
            "from blacklist b," +
            "discuss d " +
            "join tweet t on d.tweet_Id = t.tweet_Id " +
            "where b.secondParty_User_Id = #{user_Id} " +
            "and discuss_Id = #{discuss_Id} " +
            "and (b.firstParty_User_Id = d.user_Id or b.firstParty_User_Id = t.user_Id or " +
            "b.firstParty_User_Id = (select user_Id from user where user_Name = #{writeBack_User_Name} limit 1))")
    int isBlacklistByTDW(@Param("user_Id") String user_Id, @Param("writeBack_User_Name") String writeBack_User_Name, @Param("discuss_Id") String discuss_Id);
}
