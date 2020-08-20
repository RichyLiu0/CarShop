package org.carshop.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author 刘伟锐
 * @date 2020年5月07日
 */
public class BigDecimalUtils {

    public static BigDecimal val(Object value) {

        BigDecimal rs = new BigDecimal(0);

        try {
            if (value != null) {
                if (value instanceof BigDecimal) {
                    rs = (BigDecimal) value;
                } else if (value instanceof String) {
                    rs = new BigDecimal(((String) value).trim());
                } else if (value instanceof BigInteger) {
                    rs = new BigDecimal((BigInteger) value);
                } else if (value instanceof Number) {
                    rs = new BigDecimal(((Number) value).doubleValue());
                } else {
                    throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
                }
            }
        } catch (Exception ex) {
            rs = new BigDecimal(0);
        }
        return rs;
    }

    ;

}
