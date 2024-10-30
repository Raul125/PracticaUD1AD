package com.raulrh.tiendatv.base;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "televisions")
@XmlAccessorType(XmlAccessType.FIELD)
public class TelevisionList {
    @XmlElements({
            @XmlElement(name = "curvedTelevision", type = CurvedTelevision.class),
            @XmlElement(name = "gamingTelevision", type = GamingTelevision.class),
            @XmlElement(name = "smartTelevision", type = SmartTelevision.class),
    })
    private List<Television> televisions;

    public TelevisionList() {

    }

    public TelevisionList(List<Television> televisions) {
        this.televisions = televisions;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}