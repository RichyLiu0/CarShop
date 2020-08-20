package org.main.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>

 * </p>
 *
 * @author Richy
 * @since 2020-08-16
 */
@TableName("t_car_type_attr")
public class CarTypeAttr implements java.io.Serializable {


    @TableId
    private String id;
    private String attr;
    private String val;
    private String carTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }
}
