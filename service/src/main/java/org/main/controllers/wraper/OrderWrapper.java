package org.main.controllers.wraper;


import org.main.model.CarType;
import org.main.model.CarTypeAttr;
import org.main.model.Order;
import org.main.model.OrderConfig;

import java.util.List;

public class OrderWrapper extends Order {

    private List<OrderConfig> cfgs ;

    public List<OrderConfig> getCfgs() {
        return cfgs;
    }

    public void setCfgs(List<OrderConfig> cfgs) {
        this.cfgs = cfgs;
    }
}
