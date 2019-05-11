package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.dao.LocationMapper;
import top.mnilsy.cup.pojo.LocationPojo;
import top.mnilsy.cup.service.NearbyService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mnilsy on 19-5-5 上午11:03.
 */
@Service("NearbyService")
public class NearbyServiceImpl implements NearbyService {

    @Resource(name = "locationMapper")
    private LocationMapper locationMapper;

    @Override
    public boolean location(String user_Id, double x, double y) {
        LocationPojo locationPojo = new LocationPojo(user_Id, x, y);
        boolean flag = locationMapper.insertLocation(locationPojo) == 1;
        if (!flag) {
            flag = locationMapper.updateLocation(locationPojo) == 1;
        }
        return flag;
    }

    @Override
    public boolean cleanLocation(String user_Id) {
        LocationPojo locationPojo = new LocationPojo(user_Id, 0, 0);
        return locationMapper.updateLocation(locationPojo) == 1;
    }

    @Override
    public List<TweetVO> getLocationTweet(String user_Id, int count) {
        if (count < 0 || user_Id.length() != 36) return null;
        return locationMapper.getTweet(user_Id, count * 10);
    }

    @Override
    public List<TweetVO> getLocationTweet(double x, double y, int count) {
        if (count < 0) return null;
        return locationMapper.getTweets(x, y, count * 10);
    }
}
