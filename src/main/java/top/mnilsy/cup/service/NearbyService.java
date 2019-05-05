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

    List<TweetVO> getLocationTweet(String user_Id,int count);
}
