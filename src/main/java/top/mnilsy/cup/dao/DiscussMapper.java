package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.Discuss_AtVO;

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
    @Select("")
    Discuss_AtVO getDiscuss_AtVO(String discuss_Id);

    /**
     * 根据评论id获推文发布用户id
     *
     * @param discuss_Id 评论id
     * @return 评论者id
     * @author mnilsy
     */
    @Select("")
    String getTweetUserId(String discuss_Id);

    /**
     * 更改评论记录的状态discuss_Condition
     *
     * @param discuss_Id 评论id
     * @param user_Id    评论者id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update discuss set discuss_Condition='1' where discuss_Id=#{discuss_Id} and user_Id=#{user_Id}")
    int updateCondition(@Param("discuss_Id") String discuss_Id, @Param("user_Id") String user_Id);
}
