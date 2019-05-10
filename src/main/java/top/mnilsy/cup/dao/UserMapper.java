package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.*;
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
    UserPojo getUserByIdInfo(@Param("user_Id") String user_Id);

    /**
     * 根据user_Phone获取用户基本信息
     *
     * @param user_Phone 用户手机号
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Phone = #{user_Phone}")
    UserPojo getUserByPhoneInfo(@Param("user_Phone") String user_Phone);

    /**
     * 根据user_Phone获取用户基本信息
     *
     * @param user_Phone 用户手机号
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Phone = #{user_Phone}")
    UserVO getUserByPhone(@Param("user_Phone") String user_Phone);

    /**
     * 根据user_Name获取用户基本信息
     *
     * @param user_Name 用户名
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Name = #{user_Name}")
    UserVO getUserByName(@Param("user_Name")String user_Name);

    /**
     * 设置用户名
     *
     * @param user_Name 用户名
     * @param user_Id 用户id
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Update("update user set user_Name = #{user_Name} where user_Id = #{user_Id}")
    int setUserNameById(@Param("user_Name") String user_Name,@Param("user_Id") String user_Id);

    /**
     * 设置密码
     *
     * @param passwd 密码
     * @param user_Id 用户ID
     * @return passwdPojo
     * @author Jason_Jane
     */
    @Insert("insert into passwd (user_Id,passwd_Normal) values(#{user_Id},#{passwd})")
    int setPasswd(@Param("passwd")String passwd,@Param("user_Id") String user_Id);

    /**
     * 密码登录
     *
     * @param user 用户名||用户手机号码||用户电子邮箱
     * @param passwd 密码
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user LEFT JOIN passwd on user.user_Id = passwd.user_Id " +
            "where (user.user_Name = #{user} or user.user_Phone = #{user} or user.user_Email = #{user}) " +
            "and passwd.passwd_Normal = #{passwd}")
    UserPojo getUserByNamePhoneEmail(@Param("user")String user,@Param("passwd")String passwd);

    /**
     * 根据user_Id获取用户密码
     *
     * @param user_Id 用户账号id
     * @return passwd_Normal
     * @author Jason_Jane
     */
    @Select("select * from passwd where user_Id = #{user_Id}")
    PasswdPojo getPasswdById(@Param("user_Id") String user_Id);

    /**
     * 根据user_Name查询是否重名
     *
     * @param user_Name 用户名
     * @return 用户名
     * @author Jason_Jane
     */
    @Select("select * from user where user_Name = #{user_Name}")
    UserPojo getUserByUserName(@Param("user_Name") String user_Name);

    /**
     * 根据用户id获取用户名
     *
     * @param user_Id 用户id
     * @return 用户名
     * @author mnilsy
     */
    @Select("select user_Name from user where user_Id=#{user_Id}")
    String getUserName(String user_Id);

    /**
     * 根据用户名获取用户id
     *
     * @param user_Name 用户名
     * @return 用户id
     * @author mnilsy
     */
    @Select("select user_Id from user where user_Name=#{user_Name}")
    String getUser_Id(String user_Name);

    /**
     * 获取所有的可用id
     *
     * @return 所有可用的用户id
     * @author mnilsy
     */
    @Select("select user_Id from user where user_Condition=0")
    List<String> getAllUserId();


    /**
     * 修改性别
     *
     * @param user_Sex 用户性别
     * @param user_Id 用户id
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Sex = #{user_Sex} where user_Id = #{user_Id}")
    int updateUserSex(@Param("user_Sex") String user_Sex,@Param("user_Id") String user_Id);

    /**
     * 修改密码
     *
     * @param passwd_Normal 新密码
     * @param passwd_Old1 旧密码1
     * @param passwd_Old2 旧密码2
     * @param passwd_Old3 旧密码3
     * @param user_Id 用户id
     * @return passwdPojo
     * @author Jason_Jane
     */
    @Update("update passwd set passwd_Old3 = #{passwd_Old3},passwd_Old2 = #{passwd_Old2},passwd_Old1 =  #{passwd_Old1},passwd_Normal =  #{passwd_Normal} where user_Id =  #{user_Id}")
    int updatePasswd(@Param("passwd_Normal") String passwd_Normal,@Param("passwd_Old1") String passwd_Old1,@Param("passwd_Old2") String passwd_Old2,@Param("passwd_Old3") String passwd_Old3,@Param("user_Id") String user_Id);


    /**
     * 增加用户
     *
     * @param userPojo 用户info
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Insert("insert into user (user_Id,user_Name,user_NickName,user_Phone) values(#{user_Id},#{user_Name},#{user_NickName},#{user_Phone})")
    int addUserByPhoneInfo(UserPojo userPojo);

    /**
     * 找回密码
     *
     * @param passwd_Normal 新密码
     * @param passwd_Old1 旧密码1
     * @param passwd_Old2 旧密码2
     * @param passwd_Old3 旧密码3
     * @param user_Id 用户id
     * @author Jason_Jane
     */
    @Update("update passwd set passwd_Old3 = #{passwd_Old3},passwd_Old2 = #{passwd_Old2},passwd_Old1 =  #{passwd_Old1},passwd_Normal =  #{passwd_Normal} where user_Id =  #{user_Id}")
    int findPasswd(@Param("passwd_Normal") String passwd_Normal,@Param("passwd_Old1") String passwd_Old1,@Param("passwd_Old2") String passwd_Old2,@Param("passwd_Old3") String passwd_Old3,@Param("user_Id") String user_Id);

    /**
     * 修改手机号
     *
     * @param user_Phone 用户新手机号
     * @param user_Id 用户id
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Phone = #{user_Phone} where user_Id = #{user_Id}")
    int updatePhone(@Param("user_Phone")String user_Phone,@Param("user_Id")String user_Id);

    /**
     * 上传头像
     *
     * @param url 用户大头像地址
     * @param minUrl 用户小头像地址
     * @param user_Name 用户名
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_HeadUrl_max = #{url},user_HeadUrl_min = #{minUrl} where user_Name = #{user_Name}")
    int updateUserHead(@Param("url")String url,@Param("minUrl")String minUrl,@Param("user_Name")String user_Name);

    /**
     * 上传背景图
     *
     * @param url 用户背景图地址
     * @param user_Name 用户名
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Background = #{url} where user_Name = #{user_Name}")
    int updateBackground(@Param("url")String url,@Param("user_Name")String user_Name);

    /**
     * 绑定电子邮箱
     *
     * @param user_Email 用户电子邮箱
     * @param user_Id 用户id
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Email = #{user_Email} where user_Id = #{user_Id}")
    int bindUserEmail(@Param("user_Email")String user_Email,@Param("user_Id")String user_Id);

    /**
     * 修改电子邮箱
     *
     * @param user_Email 用户电子邮箱
     * @param user_Id 用户id
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Email = #{user_Email} where user_Id = #{user_Id}")
    int updateUserEmail(@Param("user_Email")String user_Email,@Param("user_Id")String user_Id);

    /**
     * 修改昵称
     *
     * @param user_NickName 用户昵称
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_NickName = #{user_NickName} where user_Id = #{user_Id}")
    int updateUserNickName(@Param("user_NickName")String user_NickName,@Param("user_Id")String user_Id);
}
