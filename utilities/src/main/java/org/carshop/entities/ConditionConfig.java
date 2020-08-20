package org.carshop.entities;

import java.io.Serializable;

public class ConditionConfig implements Serializable {

    private static final long serialVersionUID = 3473196641149160738L;
    String colName;
    Object colValue;
    String operator;

    public ConditionConfig(String colName, Object colValue, String operator) {
        this.colName = colName;
        this.colValue = colValue;
        this.operator = operator;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Object getColValue() {
        return colValue;
    }

    public void setColValue(Object colValue) {
        this.colValue = colValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

