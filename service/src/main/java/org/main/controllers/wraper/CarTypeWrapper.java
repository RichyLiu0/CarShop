package org.main.controllers.wraper;


import org.main.model.CarType;
import org.main.model.CarTypeAttr;

import java.util.List;

public class CarTypeWrapper extends CarType {

    private List<CarTypeAttr> attrs ;

    public List<CarTypeAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<CarTypeAttr> attrs) {
        this.attrs = attrs;
    }
}
