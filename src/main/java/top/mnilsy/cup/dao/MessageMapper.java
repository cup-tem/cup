package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.pojo.MessagePojo;

/**
 * Created by mnilsy on 19-4-23 下午6:21.
 */
@Repository("messageMapper")
public interface MessageMapper {
    @Insert("")
    boolean addMessage(MessagePojo messagePojo);

    @Update("")
    boolean signFor(String message_Id);
}