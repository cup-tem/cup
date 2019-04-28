package top.mnilsy.cup.BO;

import java.io.Serializable;

/**
 * Created by mnilsy on 19-4-24 下午11:35.
 */
public class AtBO implements Serializable {
    private static final long serialVersionUID = 1890499034721488862L;
    private String at_Id;
    private int at_From_Type;
    private Object data;//存放扩展包

    public AtBO() {
    }

    public AtBO(String at_Id, int at_From_Type, Object data) {
        this.at_Id = at_Id;
        this.at_From_Type = at_From_Type;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAt_Id() {
        return at_Id;
    }

    public void setAt_Id(String at_Id) {
        this.at_Id = at_Id;
    }

    public int getAt_From_Type() {
        return at_From_Type;
    }

    public void setAt_From_Type(int at_From_Type) {
        this.at_From_Type = at_From_Type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
