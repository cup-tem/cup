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
            "SELECT #{blacklistPojo.blackList_Id},#{blacklistPojo.firstParty_User_Id},#{blacklistPojo.secondParty_User_Id} " +
            "from DUAL" +
            "where not exists(select * from fans where firstParty_User_Id=#{blacklistPojo.firstParty_User_Id} and secondParty_User_Id=#{blacklistPojo.secondParty_User_Id})")
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
     * @param user_Id 用户id
     * @param count 获取次数
     * @return 用户拉黑的人的VO包
     */
    @Select("select u.user_HeadUrl_min,u.user_NickName,u.user_Name " +
            "from blacklist b join user u on b.secondParty_User_Id = u.user_Id " +
            "where b.blackList_Condition=0 and u.user_Condition=0 and firstParty_User_Id=#{user_Id} limit #{count},15")
    List<UserListVO> getBlackist(String user_Id, int count);
}
