package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.AccessoryPojo;

/**
 * Created by mnilsy on 19-4-29 下午3:50.
 */
@Repository("accessoryMapper")
@Mapper
public interface AccessoryMapper {
    /**
     * 增加附件
     *
     * @param accessoryPojos 附件数组
     * @return 增加的条数
     * @author mnilsy
     */
    @Insert("<script>" +
            "insert into accessory(accessory_Id, tweet_Id, accessory_Url) " +
            "values" +
            "<foreach collection = 'accessoryPojos' item = 'accessoryPojo' separator = ','>" +
            "(#{accessoryPojo.accessory_Id},#{accessoryPojo.tweet_Id},#{accessoryPojo.accessory_Url})" +
            "</foreach>" +
            "</script>")
    int insetAccessory(@Param("accessoryPojos") AccessoryPojo[] accessoryPojos);
}
