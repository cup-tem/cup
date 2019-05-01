package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;

import java.util.List;


/**
 * Created by mnilsy on 19-4-20 上午12:22.
 */
@Repository("userMapper")
@Mapper
public interface UserMapper {
    /**
     * 根据user_Id获取用户基本信息
     *
     * @param user_Id 用户账号id
     * @return 用户基本资料
     */
    @Select("select * from user where user_Id = #{user_Id}")
    UserPojo getUserByIdInfo(String user_Id);

    /**
     * 根据user_Name获取用户基本信息
     *
     * @param user_Name 用户名
     * @return 用户基本资料
     */
    @Select("select * from user where user_Name = #{user_Name }")
    UserVO getUserByNameInfo(String user_Name);

    /**
     * 设置用户名
     *
     * @param user_Name 用户名
     * @return 用户基本资料
     */
    @Insert("insert into user where user_Name = #{user_Name}")
    UserPojo setUser(String user_Name);

    /**
     * 根据user_Name\\user_Phone\\user_email查询用户信息
     *
     * @param user 用户名||用户手机号码||用户电子邮箱
     * @return 用户基本资料
     */
    @Select("select user_Id from user where user_Name = #{user} or user_Phone = #{user} or user_email = #{user}")
    UserPojo getUserByNamePhoneEmail(String user);

    /**
     * 根据user_Id获取用户密码
     *
     * @param user_Id 用户账号id
     * @return passwd_Normal
     */
    @Select("select passwd_Normal from passwd where user_Id = #{user_Id}")
    PasswdPojo getPasswdById(String user_Id);

    /**
     * 根据user_Name查询是否重名
     *
     * @param user_Name 用户名
     * @return 用户名
     */
    @Select("select user_Name from user where user_Name = #{user_Name}")
    UserPojo getUserNameByUserName(String user_Name);

    /**
     * 根据用户id获取发推文用户名
     *
     * @param user_Id 用户id
     * @return 用户名
     */
    @Select("")
    String getUserName(String user_Id);

    /**
     * 根据用户名获取用户id
     *
     * @param user_Name 用户名
     * @return 用户id
     * @author mnilsy
     */
    @Select("")
    String getUser_Id(String user_Name);

    /**
     * 获取所有的可用id
     *
     * @return 所有可用的用户id
     * @author mnilsy
     */
    @Select("")
    List<String> getAllUserId();


    /**
     * 修改性别
     *
     * @param userVO 用户信息
     * @return userVO
     */
    @Update("update user set user_Sex = #{user_Sex} where user_Name = #{user_Name}")
    UserVO updateUserSex(UserVO userVO);

    /**
     * 修改密码
     *
     * @param passwdPojo 用户密码信息
     * @return passwdPojo
     */
    @Update("update passwd set Passwd_Old3 = #{Passwd_Old3} and Passwd_Old2 = #{Passwd_Old2} and Passwd_Old1 = #{Passwd_Old1} and Passwd_Normal = #{Passwd_Normal} where user_Id = #{user_Id}")
    UserVO updatePasswd(PasswdPojo passwdPojo);
}
