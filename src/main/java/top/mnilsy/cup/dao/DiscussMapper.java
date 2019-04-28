package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.Discuss_AtVO;

/**
 * Created by mnilsy on 19-4-27 下午10:15.
 */
@Repository("discussMapper")
@Mapper
public interface DiscussMapper {
    /**
     * @param discuss_Id 评论id
     * @return 评论atVO包
     * @author mnilsy
     * 根据评论id获取评论atVO包
     */
    @Select("")
    Discuss_AtVO getDiscuss_AtVO(String discuss_Id);

    /**
     * @param discuss_Id
     * @return 评论者id
     * @author mnilsy
     * 根据评论id获推文发布用户id
     */
    @Select("")
    String getTweetUserId(String discuss_Id);
}
