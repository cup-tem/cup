package top.mnilsy.cup.service;


/**
 * Created by mnilsy on 19-4-25 上午1:20.
 */
public interface AtService {
    /**
     * 推文中@用户
     *
     * @param at_From_Id 艾特的推文id
     * @param user_Id 艾特的目标用户名
     * @return 是否艾特成功
     */
    boolean tweetAt(String at_From_Id,String user_Id);
}
