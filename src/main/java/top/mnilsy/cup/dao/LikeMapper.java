package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.Like_AtVO;

/**
 * Created by mnilsy on 19-4-28 上午1:33.
 */
@Repository("likeMapper")
@Mapper
public interface LikeMapper {
    /**
     * @param like_Id 赞的id
     * @return 赞的atVO包
     * @author mnilsy
     * 根据赞的id获取赞的atVO包
     */
    @Select("")
    Like_AtVO getLike_AtVO(String like_Id);

    /**
     * 根据点赞id获取推文发布用户id
     *
     * @param like_Id 赞id
     * @return 推文发布用户id
     * @author mnilsy
     */
    @Select("")
    String getTweetUserId(String like_Id);
}
