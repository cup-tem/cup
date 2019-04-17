package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午7:01.
 */
public class Proclamation {
    private String proclamation_Id;
    private String proclamation_Vlue;
    private String proclamation_Time;

    public Proclamation(String proclamation_Vlue) {
        this.proclamation_Id = String.valueOf(UUID.randomUUID());
        this.proclamation_Vlue = proclamation_Vlue;
    }

    public String getProclamation_Id() {
        return proclamation_Id;
    }

    public void setProclamation_Id(String proclamation_Id) {
        this.proclamation_Id = proclamation_Id;
    }

    public String getProclamation_Vlue() {
        return proclamation_Vlue;
    }

    public void setProclamation_Vlue(String proclamation_Vlue) {
        this.proclamation_Vlue = proclamation_Vlue;
    }

    public String getProclamation_Time() {
        return proclamation_Time;
    }

    public void setProclamation_Time(String proclamation_Time) {
        this.proclamation_Time = proclamation_Time;
    }
}
