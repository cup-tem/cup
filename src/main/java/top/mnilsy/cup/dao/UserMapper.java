package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.pojoVO.UserPojoVO;

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
    UserPojoVO getUserByIdInfo(String user_Id);

    /**
     * 根据user_Name获取用户基本信息
     *
     * @param user_Name 用户名
     * @return 用户基本资料
     */
    @Select("select * from user where user_Name = #{user_Name} or user_Phone = #{user_Phone} or user_email = #{user_email}")
    UserPojoVO getUserInfo(String user_Name);

    /**
     * 根据user_Name获取用户user_Id
     *
     * @param user_Name 用户名
     * @return user_Id
     */
    @Select("select user_Id from user where user_Name = #{user_Name}")
    UserPojo getIdByUserNameInfo(String user_Name);

    /**
     * 根据用户名，手机号或电子邮箱获取密码
     *
     * @param passwd_Normal 用户密码
     * @return 用户密码
     */
    @Select("select passwd_Normal from passwd where user_Id = #{user_Id}")
    PasswdPojo getPasswdInfo(String passwd_Normal);

    /**
     * 查询用户名是否重复
     *
     * @param user_Name 用户名
     * @return 用户名
     */
    @Select("select user_Name from user where user_Name = #{user_Name}")
    UserPojo getUserNameInfo(String user_Name);

    /**
     * 根据user_Phone获取用户基本信息
     *
     * @param user_Phone 手机号
     * @return 用户基本资料
     */
    @Select("select * from user where user_Phone = #{user_Phone}")
    UserPojoVO getUserByPhoneInfo(String user_Phone);

    /**
     * 根据user_Phone增加用户
     *
     * @param user_Phone 手机号
     * @return 新增用户
     */
    @Insert("insert into user (user_Id,user_SignlnTime,user_Condition,user_Name,user_NickName,user_Sex,user_Phone,user_Email) values (*,*,*,*,*,*,*,*)")
    UserPojo register(String user_Phone);

    /**
     * 根据user_Id修改性别
     *
     * @param user_Sex 性别
     * @return 修改性别
     */
    @Update("update user set user_Sex = #{user_Sex} where user_Id = #{user_Id}")
    UserPojoVO updateUserSex(String user_Sex);

    /**
     * 根据user_Id修改密码
     *
     * @param passwd_Normal 当前密码
     * @param passwd_Old1 旧密码1
     * @return 修改性别
     */
}
