package top.mnilsy.cup.service;

import top.mnilsy.cup.VO.TweetVO;

import java.util.List;

/**
 * Created by mnilsy on 19-5-5 上午10:57.
 */
public interface NearbyService {

    /**
     * 存储定位信息
     * @param user_Id 定位用户的id
     * @param x 纬度
     * @param y 经度
     * @return 是否存储成功
     * @author mnilsy
     */
    boolean location(String user_Id,double x,double y);

    /**
     * 清除定位信息
     * @param user_Id 用户id
     * @return 是否清除成功
     * @author mnilsy
     */
    boolean cleanLocation(String user_Id);

    /**
     * 根据用户id，获取用户附近的人的推文
     * @param user_Id 用户id
     * @param count 获取次数
     * @return 一组推文
     */
    List<TweetVO> getLocationTweet(String user_Id,int count);

    /**
     * 根据用户定位，获取用户附近的人的推文
     * @param x 纬度
     * @param y 经度
     * @param count 获取次数
     * @return 一组推文
     */
    List<TweetVO> getLocationTweet(double x,double y,int count);
}
