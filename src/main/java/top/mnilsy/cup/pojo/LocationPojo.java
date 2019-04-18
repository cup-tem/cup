package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午6:47.
 */
public class LocationPojo {
    private String location_Id;
    private String user_Id;
    private double location_X;
    private double location_Y;
    private String location_Time;
    private String newTweetTime;

    public LocationPojo() {
    }

    public LocationPojo(String user_Id, double location_X, double location_Y, String newTweetTime) {
        this.location_Id = String.valueOf(UUID.randomUUID());
        this.user_Id = user_Id;
        this.location_X = location_X;
        this.location_Y = location_Y;
        this.newTweetTime = newTweetTime;
    }

    public String getLocation_Id() {
        return location_Id;
    }

    public void setLocation_Id(String location_Id) {
        this.location_Id = location_Id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public double getLocation_X() {
        return location_X;
    }

    public void setLocation_X(double location_X) {
        this.location_X = location_X;
    }

    public double getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(double location_Y) {
        this.location_Y = location_Y;
    }

    public String getLocation_Time() {
        return location_Time;
    }

    public void setLocation_Time(String location_Time) {
        this.location_Time = location_Time;
    }

    public String getNewTweetTime() {
        return newTweetTime;
    }

    public void setNewTweetTime(String newTweetTime) {
        this.newTweetTime = newTweetTime;
    }

    @Override
    public String toString() {
        return "LocationPojo{" +
                "location_Id='" + location_Id + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", location_X=" + location_X +
                ", location_Y=" + location_Y +
                ", location_Time='" + location_Time + '\'' +
                ", newTweetTime='" + newTweetTime + '\'' +
                '}';
    }
}
