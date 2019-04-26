package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.MessagePojo;

import java.sql.Timestamp;

@Repository("RelationshipMapper")
public interface RelationshipMapper {
    /**
     * 获取用户id
     *
     * @param user_Name 用户名
     * @return 用户账号id
     */
    @Select("select user_Id from user where user_Name = #{user_Name}")
    String getUserIdByUserName(String user_Name);

    /**
     * 关注用户
     *
     * @param firstParty_User_Id 关注者
     * @param secondParty_User_Id 被关注者
     * @param fans_Time 关注时间
     * @return 是否关注成功
     */
    @Insert("insert into fans(firstParty_User_Id,secondParty_User_Id,fans_Time) " +
            "values(#{firstParty_User_Id},#{secondParty_User_Id},#{fans_Time})")
    int insertFan(String firstParty_User_Id, String secondParty_User_Id, Timestamp fans_Time);

    /**
     * 查询关注者是否已关注被关注者
     *
     * @param firstPartyUserId 关注者
     * @return 是否查询到信息
     */
    @Select("select secondParty_User_Id from fans where firstParty_User_Id=#{firstPartyUserId}")
    String selectSecondPartyUserId(String firstPartyUserId);

    /**
     * 取消关注
     *
     * @param firstPartyUserId 关注者
     * @param secondPartyUserId 被关注者
     * @return 是否取消关注
     */
    @Delete("delete from fans where firstParty_User_Id=#{firstPartyUserId} " +
            "and secondParty_User_Id=#{secondPartyUserId}")
    int deleteFan(String firstPartyUserId,String secondPartyUserId);


    /**
     * 加入黑名单
     *
     * @param firstPartyUserId 拉黑用户 id
     * @param secondPartyUserId 被拉黑用户的账号
     * @param blacklistTime 拉黑时间
     * @return 是否加入成功
     */
    @Insert("insert into blacklist(firstParty_User_Id,secondParty_User_Id,fans_Time) " +
            "values(#{firstPartyUserId},#{secondPartyUserId},#{blacklistTime})")
    int insertBlacklist(String firstPartyUserId, String secondPartyUserId, Timestamp blacklistTime);

    /**
     * 移出黑名单
     *
     * @param firstPartyUserId 拉黑用户 id
     * @param secondPartyUserId 被拉黑用户的账号
     * @return 是否移出成功
     */
    @Delete("delete from blacklist where firstParty_User_Id=#{firstPartyUserId} " +
            "and secondParty_User_Id=#{secondPartyUserId}")
    int deleteBlacklist(String firstPartyUserId,String secondPartyUserId);

}
