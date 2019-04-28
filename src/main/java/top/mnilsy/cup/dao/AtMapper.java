package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.AtPojo;

import java.util.List;

/**
 * Created by mnilsy on 19-4-25 下午1:22.
 */
@Repository("atMapper")
@Mapper
public interface AtMapper {
    /**
     * 增加一条at记录
     * @author mnilsy
     * @param atPojo 艾特pojo
     * @return 是否增加成功
     */
    @Insert("")
    int insertAt(AtPojo atPojo);

    /**
     * 签收@
     *
     * @param at_Id 艾特的id
     * @return 是否更新成功
     */
    @Update("")
    int updateAt_Condition(String at_Id);

    /**
     * 根据艾特的id查询被艾特用户名
     *
     * @param at_Id 艾特的id
     * @return 被艾特的用户名
     */
    @Select("")
    String selectUser_Name(String at_Id);

    /**
     * 根据用户id查询该用户所有未签收的艾特
     *
     * @param user_Id 用户id
     * @return 查询到的所有艾特
     */
    @Select("")
    List<AtPojo> selectNotSignforByUser_Id(String user_Id);

    /**
     * 根据用户名查询该用户所有未签收的艾特
     *
     * @param user_Name 用户名
     * @return 查询到的所有艾特
     */
    @Select("")
    List<AtPojo> selectNotSignforByUserName(String user_Name);
}
