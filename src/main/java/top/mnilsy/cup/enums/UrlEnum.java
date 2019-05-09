package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-5-7 下午5:35.
 */
public enum UrlEnum {
    ACCESSORY("/home/mnilsy/nginxFile/accessory/", "推文"),
    BACKGROUN("/home/mnilsy/nginxFile/imge/backgroun/", "背景图"),
    HEADMAX("/home/mnilsy/nginxFile/imge/headMAX/", "大头像"),
    HEADMIN("/home/mnilsy/nginxFile/imge/headMIN/", "小头像"),
    TEMP("/home/mnilsy/nginxFile/temp/","临时缓存");

    public String vlue;
    public String desc;

    UrlEnum(String vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }
}
