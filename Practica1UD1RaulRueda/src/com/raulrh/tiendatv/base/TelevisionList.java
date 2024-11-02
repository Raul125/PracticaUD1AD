package com.raulrh.tiendatv.base;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * The type Television list.
 */
@XmlRootElement(name = "televisions")
@XmlAccessorType(XmlAccessType.FIELD)
public class TelevisionList {
    @XmlElements({
            @XmlElement(name = "curvedTelevision", type = CurvedTelevision.class),
            @XmlElement(name = "gamingTelevision", type = GamingTelevision.class),
            @XmlElement(name = "smartTelevision", type = SmartTelevision.class),
    })
    private List<Television> televisions;

    /**
     * Instantiates a new Television list.
     */
    public TelevisionList() {

    }

    /**
     * Instantiates a new Television list.
     *
     * @param televisions the televisions
     */
    public TelevisionList(List<Television> televisions) {
        this.televisions = televisions;
    }

    /**
     * Gets televisions.
     *
     * @return the televisions
     */
    public List<Television> getTelevisions() {
        return televisions;
    }

    /**
     * Sets televisions.
     *
     * @param televisions the televisions
     */
    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}