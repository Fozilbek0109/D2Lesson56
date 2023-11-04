package uz.coder.d2lesson56.models;

import java.io.Serializable;

public class TovarModel implements Serializable {
    private String tovarNomi;
    private int count;        //2
    private String countType; //kg,litr,dona......

    public String getTovarNomi() {
        return tovarNomi;
    }

    public void setTovarNomi(String tovarNomi) {
        this.tovarNomi = tovarNomi;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public TovarModel(String tovarNomi, int count, String countType) {
        this.tovarNomi = tovarNomi;
        this.count = count;
        this.countType = countType;
    }
}
