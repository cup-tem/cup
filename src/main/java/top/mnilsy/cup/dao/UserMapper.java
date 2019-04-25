package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.UserPojo;

/**
 * Created by mnilsy on 19-4-20 上午12:22.
 */
@Repository("userMapper")
@Mapper
public interface UserMapper {
    /**
     * 获取用户基本信息
     *
     * @param user_Id 用户账号id
     * @return 用户基本资料
     */
    @Select("select * from user where user_Id = #{user_Id}")
    UserPojo getUserInfo(String user_Id);

    /**
     * 根据用户id获取发推文用户名
     * @param user_Id 用户id
     * @return
     */
    @Select("")
    String getUserName(String user_Id);
}
