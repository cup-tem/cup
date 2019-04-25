package top.mnilsy.cup.service.impl;

import top.mnilsy.cup.enums.AtFromTypeEnum;
import top.mnilsy.cup.pojo.AtPojo;
import top.mnilsy.cup.service.AtService;

/**
 * Created by mnilsy on 19-4-25 上午1:37.
 */
public class AtServiceImpl implements AtService {

    @Override
    public boolean tweetAt(String at_From_Id, String user_Id) {
        //组装AtPojo，存库
        AtPojo atPojo=new AtPojo(user_Id,at_From_Id, AtFromTypeEnum.TWEET.vlue);
        return false;
    }
}
