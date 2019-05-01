package top.mnilsy.cup.VO;

/**
 * Created by mnilsy on 19-4-29 上午12:11.
 */
public class ProclamationVO {
    private String proclamation_Vlue;
    private String proclamation_Time;

    public ProclamationVO() {
    }

    public ProclamationVO(String proclamation_Vlue, String proclamation_Time) {
        this.proclamation_Vlue = proclamation_Vlue;
        this.proclamation_Time = proclamation_Time;
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

    @Override
    public String toString() {
        return "ProclamationVO{" +
                "proclamation_Vlue='" + proclamation_Vlue + '\'' +
                ", proclamation_Time='" + proclamation_Time + '\'' +
                '}';
    }
}
