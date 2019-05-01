package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mnilsy.cup.VO.FollwVO;
import top.mnilsy.cup.dao.FansMapper;
import top.mnilsy.cup.dao.UserMapper;
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
    public List<FollwVO> getFollwlist(String user_Id, int count) {
        if (count < 0) return null;
        List<FollwVO> follwVOList = fansMapper.getFollw(user_Id, count * 15);
        return follwVOList;
    }
}
