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
@TableName("t_car_serial")
public class CarSerial implements java.io.Serializable {

    private static final long serialVersionUID = -8510762505654770378L;

    @TableId("ID")
    private String id;

    private String name;

    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
