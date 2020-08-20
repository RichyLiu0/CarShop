/*
 * 文  件  名:  RandomStringUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2018年3月2日
 * 修改内容:  修改内容
 */
package org.carshop.utilities;

import java.util.Random;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 *
 * @author 李锦忠, 2018年3月2日
 * @see 相关类#方法
 * @since 产品/模块
 */
public class RandomStringUtils {

    /**
     * 功能简述:返回长度为length的数字串字符<br>
     * 详细描述:<br>
     *
     * @param length
     * @return
     * @author 李锦忠
     * @CreateTime 2018年3月2日下午4:29:27
     */
    public static String randomString(int length) {
        if (length <= 0) {
            return null;
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(randomString(17));
        System.out.println(String.format("%04d", 1 + 1));
        System.out.println(Integer.valueOf("011"));
    }
}
