package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojoOV.UserPojoOV;

/**
 * Created by mnilsy on 19-4-20 上午12:22.
 */
@Repository("userMapper")
@Mapper
public interface UserMapper {
    @Select("")
    UserPojoOV getUserInfo(String user);
}
