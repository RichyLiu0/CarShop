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
@TableName("t_order_config")
public class OrderConfig implements java.io.Serializable {

    private static final long serialVersionUID = -8510762505654770380L;

    @TableId("ID")
    private String id;
    private String orderId;
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
