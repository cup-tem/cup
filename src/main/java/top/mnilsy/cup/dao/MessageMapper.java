package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.MessageVO;

import java.util.List;

/**
 * Created by mnilsy on 19-4-23 下午6:21.
 */
@Repository("messageMapper")
@Mapper
public interface MessageMapper {
    /**
     * 增加一条聊天记录
     *
     * @param messageVO 私信的VO包
     * @return 增加条数
     * @author mnilsy
     */
    @Insert("insert into message (message_Id, message_Vlue, message_Sender_u_Id, message_Recipient_u_Id, message_Time)" +
            "(select #{message_Id}, #{message_Vlue}, u1.user_Id as message_Sender_u_Id, u2.user_Id as message_Recipient_u_Id, #{message_Time}" +
            "from user u1," +
            "user u2" +
            "where u1.user_Name = #{sender_Name}" +
            "and u2.user_Name = #{recipient_Name})")
    int insertMessage(MessageVO messageVO);

    /**
     * 更改私信的状态
     *
     * @param message_Id 私信的id
     * @return 更改条数
     * @author mnilsy
     */
    @Update("update message set message_Condition=1 where message_Id=#{message_Id}")
    int updataCondition(String message_Id);

    /**
     * 获取指定接收用户所有状态为0的私信
     *
     * @param user_Name 接受用户名
     * @return 私信的VO包
     * @author mnilsy
     */
    @Select("select message_Id, message_Time, message_Vlue, u1.user_Name as sender_Name,u1.user_NickName as sender_NickName,#{user_Name} as recipient_Name,u2.user_NickName as recipient_NickName" +
            "from message," +
            "user u1," +
            "user u2" +
            "where u2.user_Name = #{user_Name}" +
            "and message_Recipient_u_Id = u2.user_Id" +
            "and message_Condition = 0" +
            "and u1.user_Id = message_Sender_u_Id")
    List<MessageVO> getRecipientAllCondition_0(String user_Name);
}
