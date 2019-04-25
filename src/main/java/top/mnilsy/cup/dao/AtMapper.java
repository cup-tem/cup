package top.mnilsy.cup.dao;

import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.AtPojo;

/**
 * Created by mnilsy on 19-4-25 下午1:22.
 */
@Repository("atMapper")
public interface AtMapper {
    /**
     * 增加一条at记录
     * @param atPojo 艾特pojo
     * @return
     */
    boolean addAt(AtPojo atPojo);

    /**
     * 签收@
     * @param at_Id 艾特的id
     * @return
     */
    boolean updateAt_Condition(String at_Id);
}
