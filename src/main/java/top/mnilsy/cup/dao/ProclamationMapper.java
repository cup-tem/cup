package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.ProclamationVO;

/**
 * Created by mnilsy on 19-4-29 上午12:19.
 */
@Repository("proclamationMapper")
@Mapper
public interface ProclamationMapper {

    /**
     * 根据id获取公告VO包
     *
     * @param proclamation_Id 公告id
     * @return 公共VO包
     * @author mnilsy
     */
    @Select("")
    ProclamationVO getProclamationVO(String proclamation_Id);
}
