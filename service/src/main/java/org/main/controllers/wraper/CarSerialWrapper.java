package org.main.controllers.wraper;

import org.main.model.CarSerial;
import org.main.model.CarSerialAttr;

import java.util.List;

public class CarSerialWrapper extends  CarSerial {

    private List<CarSerialAttr> attrs ;

    public List<CarSerialAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<CarSerialAttr> attrs) {
        this.attrs = attrs;
    }
}
