package top.mnilsy.cup.service.impl;


import org.springframework.stereotype.Service;
import top.mnilsy.cup.dao.RelationshipMapper;
import top.mnilsy.cup.service.RelationshipService;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.sql.Timestamp;


@Service("relationshipService")
public class RelationshipServiceImpl implements RelationshipService {
    @Resource(name="RelationshipMapper")
    private RelationshipMapper relationshipMapper;

    @Override
    public String getUserId(String user_Name) {
        return relationshipMapper.getUserIdByUserName(user_Name);
    }

    @Override
    public boolean addFans(String firstParty_User_Id, String secondParty_User_Id, Timestamp fans_Time) {
        boolean flag=false;
        if (relationshipMapper.insertFan(firstParty_User_Id,secondParty_User_Id,fans_Time)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean findSecondPartyUserId(String firstPartyUserId) {
        boolean flag=false;
        if (relationshipMapper.selectSecondPartyUserId(firstPartyUserId)==null){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean deleteFollow(String firstPartyUserId, String secondPartyUserId) {
        boolean flag=false;
        if (relationshipMapper.deleteFan(firstPartyUserId,secondPartyUserId)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean addBlacklist(String firstPartyUserId, String secondPartyUserId, Timestamp blacklistTime) {
        boolean flag=false;
        if (relationshipMapper.insertBlacklist(firstPartyUserId,secondPartyUserId,blacklistTime)>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean moveBlacklist(String firstPartyUserId, String secondPartyUserId) {
        boolean flag=false;
        if (relationshipMapper.deleteBlacklist(firstPartyUserId,secondPartyUserId)>0){
            flag=true;
        }
        return flag;
    }
}
