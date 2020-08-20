package org.main.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>

 * </p>
 *
 * @author Richy
 * @since 2020-08-16
 */
@TableName("t_order_operate")
public class OrderOperate implements java.io.Serializable {

    private static final long serialVersionUID = -8510762505654770379L;

    @TableId("ID")
    private String id;
    private  String orderId;
    private String operatorType;
    private Date operatorDate;
    private String configName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
