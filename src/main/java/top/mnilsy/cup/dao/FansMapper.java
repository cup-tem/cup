package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.FollwVO;
import top.mnilsy.cup.pojo.FansPojo;

import java.util.List;

/**
 * Created by mnilsy on 19-5-1 下午7:59.
 */
@Repository("fansMapper")
@Mapper
public interface FansMapper {

    /**
     * 增加关注记录
     *
     * @param fansPojo 关注的pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("INSERT INTO fans (fans_Id, firstParty_User_Id, secondParty_User_Id) " +
            "SELECT #{fansPojo.fans_Id},#{fansPojo.firstParty_User_Id},#{fansPojo.secondParty_User_Id} " +
            "from DUAL" +
            "where not exists(select * from fans where firstParty_User_Id=#{fansPojo.firstParty_User_Id} and secondParty_User_Id=#{fansPojo.secondParty_User_Id})")
    int insertFans(FansPojo fansPojo);

    /**
     * 更改关注状态
     *
     * @param firstParty_User_Id  关注者id
     * @param secondParty_User_Id 被关注者id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update fans set fans_Condition=(case when fans_Condition=0 then 1 else 0 end) " +
            "where firstParty_User_Id=#{firstParty_User_Id} and secondParty_User_Id=#{secondParty_User_Id}")
    int updateCondition(@Param("firstParty_User_Id") String firstParty_User_Id, @Param("secondParty_User_Id") String secondParty_User_Id);

    /**
     * 根据用户id获取用户关注的人，每次获取15个
     * @param user_Id 用户id
     * @param count 获取次数
     * @return 用户关注的人的VO包
     */
    @Select("select u.user_HeadUrl_min,u.user_NickName,u.user_Name " +
            "from fans f join user u on f.firstParty_User_Id = u.user_Id " +
            "where f.fans_Condition=0 and u.user_Condition=0 and firstParty_User_Id=#{user_Id} limit #{count},15")
    List<FollwVO> getFollw(String user_Id,int count);
}
