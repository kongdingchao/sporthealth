package com.sporthealth.enums;

/**
* @description: 性别枚举
* @author: kongdingchao
* @create: 2019/1/15
**/
public enum SexEnum {
    MAN(0,"男"),
    WOMAN(1,"女"),
    OTHER(-1,"人妖");//搞笑了

    private int sex;
    private String info;

    SexEnum(int sex, String info) {
        this.sex = sex;
        this.info = info;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static SexEnum sexOf(int sex)
    {
        for (SexEnum tmp : values())
        {
            if (tmp.getSex() == sex)
            {
                return tmp;
            }
        }
        return null;
    }
}
