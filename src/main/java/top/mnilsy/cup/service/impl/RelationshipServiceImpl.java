package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mnilsy.cup.VO.UserListVO;
import top.mnilsy.cup.dao.BlacklistMapper;
import top.mnilsy.cup.dao.FansMapper;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.BlacklistPojo;
import top.mnilsy.cup.pojo.FansPojo;
import top.mnilsy.cup.service.RelationshipService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mnilsy on 19-5-1 下午8:00.
 */
@Service("relationshipService")
public class RelationshipServiceImpl implements RelationshipService {

    @Resource(name = "fansMapper")
    private FansMapper fansMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "blacklistMapper")
    private BlacklistMapper blacklistMapper;

    @Override
    @Transactional
    public boolean fans(String firstParty_User_Id, String secondParty_User_Name) {
        FansPojo fansPojo = new FansPojo(firstParty_User_Id, userMapper.getUser_Id(secondParty_User_Name));
        boolean flag = fansMapper.insertFans(fansPojo) == 1;
        if (!flag) {
            flag = fansMapper.updateCondition(fansPojo.getFirstParty_User_Id(), fansPojo.getSecondParty_User_Id()) == 1;
        }
        return flag;
    }

    @Override
    public List<UserListVO> getFollwlist(String user_Id, int count) {
        if (count < 0) return null;
        return fansMapper.getFollw(user_Id, count * 15);
    }

    @Override
    public List<UserListVO> getFans(String user_Id, int count) {
        if (count < 0) return null;
        return fansMapper.getFans(user_Id, count * 15);
    }

    @Override
    public boolean blacklist(String firstParty_User_Id, String secondParty_User_Name) {
        BlacklistPojo blacklistPojo = new BlacklistPojo(firstParty_User_Id, userMapper.getUser_Id(secondParty_User_Name));
        boolean flag = blacklistMapper.insertBlacklist(blacklistPojo) == 1;
        if (!flag) {
            flag = blacklistMapper.updateCondition(blacklistPojo.getFirstParty_User_Id(), blacklistPojo.getSecondParty_User_Id()) == 1;
        }
        return flag;
    }

    @Override
    public List<UserListVO> getBalcklist(String user_Id, int count) {
        if (count < 0) return null;
        return blacklistMapper.getBlackist(user_Id, count * 15);
    }

    @Override
    public boolean isBalcklistByTweet(String user_Id, String tweet_Id) {
        if (user_Id.length() != 36 || tweet_Id.length() != 36) return false;
        return blacklistMapper.isBlacklistByTweet(user_Id, tweet_Id) == 1;
    }

    @Override
    public boolean isBlacklistByTDW(String user_Id, String writeBack_User_Name, String discuss_Id) {
        if (user_Id.length()!=36||discuss_Id.length()!=36||writeBack_User_Name==null)return false;
        return blacklistMapper.isBlacklistByTDW(user_Id,writeBack_User_Name,discuss_Id)==1;
    }
}
