package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.Writeback_AtVO;

/**
 * Created by mnilsy on 19-4-28 上午12:28.
 */
@Repository("writebackMapper")
@Mapper
public interface WritebackMapper {
    /**
     * @param writeBack_Id 回复的id
     * @return 回复的atVO包
     * @author mnilsy
     * 根据回复的id获取回复的atVO包
     */
    Writeback_AtVO getWriteback_AtVO(String writeBack_Id);

    /**
     * 根据回复获取评论者id
     *
     * @param writeBack_Id 回复id
     * @return 评论者id
     * @author mnilsy
     */
    @Select("")
    String getDiscussUserId(String writeBack_Id);

    /**
     * 更改评论回复记录的状态writeBack_Condition
     *
     * @param writeBack_Id 评论id
     * @param user_Id      评论者id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update writeback set writeBack_Condition='1' where writeBack_Id=#{writeBack_Id} and user_Id=#{user_Id}")
    int updateCondition(@Param("writeBack_Id") String writeBack_Id, @Param("user_Id") String user_Id);
}
