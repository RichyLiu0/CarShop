package org.carshop.utilities;

import java.math.BigInteger;

/**
 * ClassName:StringToIntUtils
 * Description:解析二进制字符串数值，转化为int值
 *
 * @author ly-zreq
 * @date 2019年7月5日
 */
public class StringToIntUtils {

    /**
     * @param decimalSource
     * @return String
     * @Description: 十进制转换成二进制 ()
     */
    public static String decimalToBinary(int decimalSource) {
        //转换成BigInteger类型
        BigInteger bi = new BigInteger(String.valueOf(decimalSource));
        //参数2指定的是转化成X进制，默认10进制
        return bi.toString(2);
    }

    /**
     * @param binarySource
     * @return int
     * @Description: 二进制转换成十进制
     */
    public static int binaryToDecimal(String binarySource) {
        //转换为BigInteger类型
        BigInteger bi = new BigInteger(binarySource, 2);
        //转换成十进制
        return Integer.parseInt(bi.toString());
    }
}
