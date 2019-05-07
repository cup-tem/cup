package top.mnilsy.cup.service;

import top.mnilsy.cup.VO.UserListVO;

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
    List<UserListVO> getFollwlist(String user_Id, int count);

    /**
     * 获取关注用户的人的列表，每次15个
     *
     * @param user_Id 用户id
     * @param count   获取次数
     * @return 关注你的人的VO
     * @author mnilsy
     */
    List<UserListVO> getFans(String user_Id, int count);


    /**
     * 拉黑/移黑
     *
     * @param firstParty_User_Id    拉黑者id
     * @param secondParty_User_Name 被拉黑者id
     * @return 是否拉黑/移黑成功
     * @author mnilsy
     */
    boolean blacklist(String firstParty_User_Id, String secondParty_User_Name);

    /**
     * 获取黑名单列表，每次15个
     *
     * @param user_Id 用户id
     * @param count   获取次数
     * @return 黑名单  的VO
     * @author mnilsy
     */
    List<UserListVO> getBalcklist(String user_Id, int count);

    /**
     * 判断该用户与指定推文的的发布者是否是黑名单关系
     * @param user_Id 用户id
     * @param tweet_Id 推文id
     * @return 是否为黑名单关系
     * @author mnilsy
     */
    boolean isBalcklistByTweet(String user_Id,String tweet_Id);

    /**
     * 判断该用户与回复者、评论者、推文发布者是否是黑名单关系
     * @param user_Id 用户id
     * @param writeBack_User_Name 推文id
     * @param discuss_Id 评论id
     * @return 是否为黑名单关系
     * @author mnilsy
     */
    boolean isBlacklistByTDW(String user_Id,String writeBack_User_Name,String discuss_Id);

}
