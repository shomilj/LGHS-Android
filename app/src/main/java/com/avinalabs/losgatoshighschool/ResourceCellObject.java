//
// ResourceCellObject.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ResourceCellObject - holds a resource cell object
//

package com.avinalabs.losgatoshighschool;

import java.io.Serializable;
import java.util.HashMap;

public class ResourceCellObject implements Serializable {

    private String label;
    private HashMap<String, String> linkContent;

    public ResourceCellObject(String label, HashMap<String, String> linkContent) {
        this.label = label;
        this.linkContent = linkContent;
    }

    public String getLabel() {
        return label;
    }

    public HashMap<String, String> getLinkContent() {
        return linkContent;
    }

    @Override
    public String toString() {
        return "ResourceCellObject{" +
                "label='" + label + '\'' +
                ", linkContent=" + linkContent +
                '}';
    }
}
