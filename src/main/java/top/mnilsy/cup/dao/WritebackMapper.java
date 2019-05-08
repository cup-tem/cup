package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.Writeback_AtVO;
import top.mnilsy.cup.pojo.DiscussPojo;
import top.mnilsy.cup.pojo.WritebackPojo;

/**
 * Created by mnilsy on 19-4-28 上午12:28.
 */
@Repository("writebackMapper")
@Mapper
public interface WritebackMapper {

    /**
     * 根据回复的id获取回复的atVO包
     *
     * @param writeBack_Id 回复的id
     * @return 回复的atVO包
     * @author mnilsy
     */
    @Select("select user.user_HeadUrl_min," +
            "user.user_Name," +
            "user.user_NickName," +
            "writeback.writeBack_Time," +
            "writeback.writeBack_Vlue," +
            "writeback.writeBack_User_Id," +
            "discuss.discuss_Id," +
            "discuss.discuss_Vlue " +
            "from user " +
            "join writeback on user.user_Id = writeback.user_Id " +
            "join discuss on writeback.discuss_Id = discuss.discuss_Id " +
            "where writeBack_Id=#{writeBack_Id}")
    Writeback_AtVO getWriteback_AtVO(String writeBack_Id);

    /**
     * 根据回复id获取评论者id
     *
     * @param writeBack_Id 回复id
     * @return 评论者id
     * @author mnilsy
     */
    @Select("select user.user_Id " +
            "from user " +
            "join discuss on user.user_Id = discuss.user_Id " +
            "join writeback on discuss.discuss_Id = writeback.discuss_Id " +
            "where writeback.writeBack_Id = #{writeBack_Id}")
    String getDiscussUserId(String writeBack_Id);

    /**
     * 更改评论回复记录的状态writeBack_Condition为1
     *
     * @param writeBack_Id 评论id
     * @param user_Id      评论者id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update writeback set writeBack_Condition=1 where writeBack_Id=#{writeBack_Id} and user_Id=#{user_Id}")
    int updateCondition(@Param("writeBack_Id") String writeBack_Id, @Param("user_Id") String user_Id);

    /**
     * 增加一条评论回复记录
     *
     * @param writebackPojo 评论回复的pojo包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("insert into writeback (writeBack_Id, discuss_Id, user_Id, writeBack_User_Id, writeBack_Vlue) " +
            "(select #{writeBack_Id},#{discuss_Id},#{user_Id},user_Id,#{writeBack_Vlue} " +
            "from user where user_Name=#{writeBack_User_Name})")
    int insetrWriteback(WritebackPojo writebackPojo);
}
