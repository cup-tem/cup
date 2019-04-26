package top.mnilsy.cup.service;

import top.mnilsy.cup.pojoVO.MessagePojoVO;

import java.sql.Timestamp;
import java.util.List;


public interface RelationshipService {
    /**
     * 获取用户账号id
     *
     * @param user_Name
     * @return 用户账号id
     */
    String getUserId(String user_Name);

    /**
     * 关注用户
     * @param firstPartyUserId 关注者账号
     * @param secondPartyUserId 被关注者账号
     * @param fansTime 关注时间
     * @return 是否关注成功
     */
    boolean addFans(String firstPartyUserId, String secondPartyUserId, Timestamp fansTime);

    /**
     * 查询关注者是否已关注被关注者
     *
     * @param firstPartyUserId 关注者
     * @return 是否关注
     */
    boolean findSecondPartyUserId(String firstPartyUserId);

    /**
     * 取消关注
     *
     * @param firstPartyUserId 关注者
     * @param secondPartyUserId 被关注者
     * @return 是否取消关注
     */
    boolean deleteFollow(String firstPartyUserId,String secondPartyUserId);

    /**
     * 加入黑名单
     *
     * @param firstPartyUserId 拉黑用户的账号
     * @param secondPartyUserId 被拉黑用户的账号
     * @param blacklistTime 拉黑时间
     * @return 是否加入成功
     */
    boolean addBlacklist(String firstPartyUserId, String secondPartyUserId, Timestamp blacklistTime);

    /**
     * 移出黑名单
     * @param firstPartyUserId 拉黑用户id
     * @param secondPartyUserId 被拉黑用户 id
     * @return 是否移出成功
     */
    boolean moveBlacklist(String firstPartyUserId,String secondPartyUserId);

}
