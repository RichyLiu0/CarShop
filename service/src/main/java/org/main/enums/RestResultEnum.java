package org.main.enums;

/**
 *
 * @author 刘伟锐
 * @date 2020/08/13
 */

public enum RestResultEnum {

    failth(0,"失败"),
    sucess(1,"成功");



    private Integer value;
    private String desc;

    RestResultEnum(Integer value, String desc) {
        this.value = value;
        this.desc=desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static RestResultEnum getEnumByValue(String  value) {
        for (RestResultEnum bt : values()) {
            if (bt.getValue().equals(value)) {
                return bt;
            }
        }
        return null;
    }

}
