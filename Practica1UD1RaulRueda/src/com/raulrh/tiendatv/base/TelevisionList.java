package com.raulrh.tiendatv.base;

import jakarta.xml.bind.annotation.*;
import java.util.List;

/**
 * The TelevisionList class is a container for a list of various types of
 * televisions, including CurvedTelevision, GamingTelevision, and SmartTelevision.
 * It is configured for XML binding, allowing instances of this class to be
 * serialized to and deserialized from XML, making it easy to store and
 * retrieve data related to different Television types.
 */
@XmlRootElement(name = "televisions")
@XmlAccessorType(XmlAccessType.FIELD)
public class TelevisionList {

    /**
     * A list that holds various types of televisions.
     * It includes elements of type CurvedTelevision, GamingTelevision,
     * and SmartTelevision, each represented in the XML by its respective
     * element name.
     */
    @XmlElements({
            @XmlElement(name = "curvedTelevision", type = CurvedTelevision.class),
            @XmlElement(name = "gamingTelevision", type = GamingTelevision.class),
            @XmlElement(name = "smartTelevision", type = SmartTelevision.class),
    })
    private List<Television> televisions;

    /**
     * Default no-argument constructor required for JAXB.
     */
    public TelevisionList() {
    }

    /**
     * Constructs a TelevisionList with the specified list of televisions.
     *
     * @param televisions a list of televisions to initialize the TelevisionList
     */
    public TelevisionList(List<Television> televisions) {
        this.televisions = televisions;
    }

    /**
     * Returns the list of televisions in this TelevisionList.
     *
     * @return the list of Television objects
     */
    public List<Television> getTelevisions() {
        return televisions;
    }

    /**
     * Sets the list of televisions in this TelevisionList.
     *
     * @param televisions the list of televisions to set
     */
    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}