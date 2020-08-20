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
@TableName("t_package")
public class Package implements java.io.Serializable {

    private static final long serialVersionUID = -8510762505654770379L;

    @TableId("ID")
    private String id;
    private String packageNo;
    private String targetPlace;
    private String orderId;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getTargetPlace() {
        return targetPlace;
    }

    public void setTargetPlace(String targetPlace) {
        this.targetPlace = targetPlace;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
