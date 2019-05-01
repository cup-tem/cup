package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mnilsy.cup.VO.DiscussVO;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.dao.*;
import top.mnilsy.cup.pojo.*;
import top.mnilsy.cup.service.AtService;
import top.mnilsy.cup.service.TweetService;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;

/**
 * Created by mnilsy on 19-4-29 上午9:38.
 */
@Service("tweetService")
public class TweetServiceImpl implements TweetService {
    @Resource(name = "tweetMapper")
    private TweetMapper tweetMapper;

    @Resource(name = "atService")
    private AtService atService;

    @Resource(name = "accessoryMapper")
    private AccessoryMapper accessoryMapper;

    @Resource(name = "likeMapper")
    private LikeMapper likeMapper;

    @Resource(name = "discussMapper")
    private DiscussMapper discussMapper;

    @Resource(name = "writebackMapper")
    private WritebackMapper writebackMapper;

    @Override
    public TweetVO getTweet(String tweet_Id) {
        if (tweet_Id.length() < 36) return null;
        return tweetMapper.getTweetVO(tweet_Id);
    }

    @Override
    public List<DiscussVO> getTweetDiscuss(String tweet_Id, int count) {
        if (count < 0 || tweet_Id.length() < 36) return null;
        return discussMapper.getTweetDiscuss(tweet_Id, count * 10);
    }

    @Transactional
    @Override
    public boolean addTweet(int tweet_Type, String tweet_Text, String[] accessory, String[] atUser_Name, String user_Id) {
        TweetPojo tweetPojo = new TweetPojo(tweet_Type, tweet_Text, user_Id);
        boolean flag = tweetMapper.insertTweet(tweetPojo) == 1;
        if (flag) {
            AccessoryPojo[] accessoryPojos = new AccessoryPojo[accessory.length];
            for (int i = 0; i < accessory.length; i++) {
                //待续。。。。。附件存储
                accessoryPojos[i] = new AccessoryPojo(tweetPojo.getTweet_Id(), accessory[i]);
            }
            int count = 0;
            try {
                count = accessoryMapper.insetAccessory(accessoryPojos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (count == 0) {
                flag = false;
            } else {
                for (String user_Name : atUser_Name) {
                    atService.tweetAt(tweetPojo.getTweet_Id(), user_Name);
                }
            }
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean putLike(String tweet_Id, String user_Id) {
        LikePojo likePojo = new LikePojo(tweet_Id, user_Id);
        int count = likeMapper.insertLike(likePojo);
        if (count == 0) {
            return likeMapper.updateCondition(tweet_Id, user_Id) == 1;
        }
        return atService.likeAt(likePojo.getLike_Id());
    }

    @Override
    public boolean deleteTweet(String tweet_Id, String user_Id) {
        return tweetMapper.updateCondition(tweet_Id, user_Id) == 1;
    }

    @Override
    public boolean deleteDiscuss(String discuss_Id, String user_Id) {
        return discussMapper.updateCondition(discuss_Id, user_Id) == 1;
    }

    @Override
    public boolean deleteWriteback(String writeBack_Id, String user_Id) {
        return writebackMapper.updateCondition(writeBack_Id, user_Id) == 1;
    }

    @Override
    @Transactional
    public boolean putDiscuss(String tweet_Id, String user_Id, String discuss_Vlue) {
        DiscussPojo discussPojo = new DiscussPojo(tweet_Id, user_Id, discuss_Vlue);
        boolean flag = discussMapper.insetrDiscuss(discussPojo) == 1;
        if (flag) {
            flag = atService.discussAt(discussPojo.getDiscuss_Id());
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean putWriteback(String discuss_Id, String user_Id, String writeBack_User_Id, String writeBack_Vlue) {
        WritebackPojo writebackPojo = new WritebackPojo(discuss_Id, user_Id, writeBack_User_Id, writeBack_Vlue);
        boolean flag = writebackMapper.insetrWriteback(writebackPojo) == 1;
        if (flag) {
            flag = atService.writebackAt(writebackPojo.getWriteBack_Id());
        }
        return flag;
    }
}
