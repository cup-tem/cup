package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
