package top.mnilsy.cup.service;

import org.springframework.stereotype.Service;
import top.mnilsy.cup.dao.MessageMapper;

import javax.annotation.Resource;

/**
 * Created by mnilsy on 19-4-23 下午6:20.
 */
@Service("messageService")
public class MessageService {
    @Resource(name = "messageMapper")
    private MessageMapper messageMapper;


}
