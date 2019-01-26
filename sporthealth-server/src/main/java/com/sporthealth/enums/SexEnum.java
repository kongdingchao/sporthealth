package com.sporthealth.enums;

/**
* @description: 性别枚举
* @author: kongdingchao
* @create: 2019/1/15
**/
public enum SexEnum implements IEnum{
    MAN(0,"男"),
    WOMAN(1,"女"),
    OTHER(-1,"人妖");//搞笑了

    private int key;
    private String value;
    private SexEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public static SexEnum enumOfKey(int key) {
        for (SexEnum tmp : values()) {
            if (tmp.getKey() == key) {
                return tmp;
            }
        }
        return null;
    }
    public static SexEnum enumOfValue(int value) {
        for (SexEnum tmp : values()) {
            if (tmp.getValue().equals(value)) {
                return tmp;
            }
        }
        return null;
    }
}
