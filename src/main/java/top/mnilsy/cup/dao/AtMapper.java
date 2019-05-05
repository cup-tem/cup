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
     *
     * @param atPojo 艾特pojo
     * @return 是否增加成功
     * @author mnilsy
     */
    @Insert("insert into at(at_Id, user_Id, at_From_Id, at_From_Type) values (#{at_Id},#{user_Id},#{at_From_Id},#{at_From_Type})")
    int insertAt(AtPojo atPojo);

    /**
     * 更改@的状态
     *
     * @param at_Id 艾特的id
     * @return 更新条数
     * @author mnilsy
     */
    @Update("update at set at_Condition=1 where at_Id=#{at_Id}")
    int updateCondition(String at_Id);

    /**
     * 根据艾特的id查询被艾特用户名
     *
     * @param at_Id 艾特的id
     * @return 被艾特的用户名
     * @author mnilsy
     */
    @Select("select user.user_Name from user join at on user.user_Id = at.user_Id where at.at_Id=#{at_Id}")
    String selectUser_Name(String at_Id);

    /**
     * 根据用户id查询该用户所有未签收的艾特
     *
     * @param user_Id 用户id
     * @return 查询到的所有艾特
     * @author mnilsy
     */
    @Select("select * from at where at_Condition=0 and user_Id=#{user_Id}")
    List<AtPojo> selectNotSignforByUser_Id(String user_Id);

    /**
     * 根据用户名查询该用户所有未签收的艾特
     *
     * @param user_Name 用户名
     * @return 查询到的所有艾特
     * @author mnilsy
     */
    @Select("select at.* from at join user on at.user_Id = user.user_Id " +
            "where at.at_Condition=0 and user.user_Name=#{user_Name}")
    List<AtPojo> selectNotSignforByUserName(String user_Name);
}
