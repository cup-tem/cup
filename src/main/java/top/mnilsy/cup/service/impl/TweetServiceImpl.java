package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.dao.AccessoryMapper;
import top.mnilsy.cup.dao.LikeMapper;
import top.mnilsy.cup.dao.TweetMapper;
import top.mnilsy.cup.pojo.AccessoryPojo;
import top.mnilsy.cup.pojo.LikePojo;
import top.mnilsy.cup.pojo.TweetPojo;
import top.mnilsy.cup.service.AtService;
import top.mnilsy.cup.service.TweetService;

import javax.annotation.Resource;
import java.util.Base64;

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

    @Override
    public TweetVO getTweet(String tweet_Id) {
        return null;
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
        LikePojo likePojo=new LikePojo(tweet_Id,user_Id);
        int count=likeMapper.insertLike(likePojo);
        if(count==0){
            return likeMapper.updateCondition(tweet_Id,user_Id)==1;
        }
        return atService.likeAt(likePojo.getLike_Id());
    }
}
