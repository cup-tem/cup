package top.mnilsy.cup.pojoOV;

/**
 * Created by mnilsy on 19-4-17 下午1:39.
 */
public class UserPojoVO {
    private String user_Name;
    private String user_NickName;
    private String user_Sex;
    private String user_Phone;
    private String user_Email;
    private String user_HeadUrl_max;
    private String user_HeadUrl_min;
    private String user_BackgroundUrl;
    private String user_QRCode;
    private String sessionId;

    public UserPojoVO() {
    }

    public UserPojoVO(String user_Name, String user_NickName, String user_Sex, String user_Phone, String user_Email, String user_HeadUrl_max, String user_HeadUrl_min, String user_BackgroundUrl, String user_QRCode, String sessionId) {
        this.user_Name = user_Name;
        this.user_NickName = user_NickName;
        this.user_Sex = user_Sex;
        this.user_Phone = user_Phone;
        this.user_Email = user_Email;
        this.user_HeadUrl_max = user_HeadUrl_max;
        this.user_HeadUrl_min = user_HeadUrl_min;
        this.user_BackgroundUrl = user_BackgroundUrl;
        this.user_QRCode = user_QRCode;
        this.sessionId = sessionId;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_NickName() {
        return user_NickName;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }

    public String getUser_Sex() {
        return user_Sex;
    }

    public void setUser_Sex(String user_Sex) {
        this.user_Sex = user_Sex;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_HeadUrl_max() {
        return user_HeadUrl_max;
    }

    public void setUser_HeadUrl_max(String user_HeadUrl_max) {
        this.user_HeadUrl_max = user_HeadUrl_max;
    }

    public String getUser_HeadUrl_min() {
        return user_HeadUrl_min;
    }

    public void setUser_HeadUrl_min(String user_HeadUrl_min) {
        this.user_HeadUrl_min = user_HeadUrl_min;
    }

    public String getUser_BackgroundUrl() {
        return user_BackgroundUrl;
    }

    public void setUser_BackgroundUrl(String user_BackgroundUrl) {
        this.user_BackgroundUrl = user_BackgroundUrl;
    }

    public String getUser_QRCode() {
        return user_QRCode;
    }

    public void setUser_QRCode(String user_QRCode) {
        this.user_QRCode = user_QRCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UserPojoVO{" +
                "user_Name='" + user_Name + '\'' +
                ", user_NickName='" + user_NickName + '\'' +
                ", user_Sex='" + user_Sex + '\'' +
                ", user_Phone='" + user_Phone + '\'' +
                ", user_Email='" + user_Email + '\'' +
                ", user_HeadUrl_max='" + user_HeadUrl_max + '\'' +
                ", user_HeadUrl_min='" + user_HeadUrl_min + '\'' +
                ", user_BackgroundUrl='" + user_BackgroundUrl + '\'' +
                ", user_QRCode='" + user_QRCode + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}