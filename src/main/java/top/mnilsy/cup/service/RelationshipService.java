package top.mnilsy.cup.service;

import top.mnilsy.cup.VO.FollwVO;

import java.util.List;

/**
 * Created by mnilsy on 19-5-1 下午7:59.
 */
public interface RelationshipService {

    /**
     * 关注/取消关注
     *
     * @param firstParty_User_Id    关注者id
     * @param secondParty_User_Name 被关注者id
     * @return 是否关注/取消关注成功
     * @author mnilsy
     */
    boolean fans(String firstParty_User_Id, String secondParty_User_Name);

    /**
     * 获取用户关注的人的列表，每次15个
     *
     * @param user_Id 用户id
     * @param count   获取次数
     * @return 关注的人的VO
     * @author mnilsy
     */
    List<FollwVO> getFollwlist(String user_Id, int count);
}
