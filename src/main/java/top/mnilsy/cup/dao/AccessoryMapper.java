package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.AccessoryPojo;

import java.util.List;

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
            "(#{accessory_Id},#{tweet_Id},#{accessory_Url})" +
            "</foreach>" +
            "</script>")
    int insetAccessory(AccessoryPojo[] accessoryPojos);

    /**
     * 获取指定推文的所有附件
     *
     * @param tweet_Id 推文id
     * @return 该推文所有附件
     * @author mnilsy
     */
    @Select("select accessory_Url from accessory where tweet_Id=#{tweet_Id}")
    List<String> getAccessoryListUrl(String tweet_Id);

    /**
     * 获取指定附件的url
     *
     * @param accessory_Id 附件的id
     * @return 附件的url
     * @author mnilsy
     */
    @Select("select accessory_Url from accessory where accessory_Id=#{accessory_Id}")
    String getAccessoryUrl(String accessory_Id);

    /**
     * 获取指定推文的所有附件的id
     *
     * @param tweet_Id 推文id
     * @return 该推文所有附件
     * @author mnilsy
     */
    @Select("select accessory_Id from accessory where tweet_Id=#{tweet_Id}")
    List<String> getAccessory_Id(String tweet_Id);
}
