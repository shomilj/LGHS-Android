//
// ResourceStackCellObject.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ResourceStackCellObject - holds a stack cell object
//

package com.sjf.lgcats;

import java.io.Serializable;
import java.util.ArrayList;

public class ResourceStackCellObject implements Serializable {

    private String label;
    private ArrayList<ResourceCellObject> list;

    public ResourceStackCellObject(String label, ArrayList<ResourceCellObject> list) {
        this.label = label;
        this.list = list;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<ResourceCellObject> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "ResourceStackCellObject{" +
                "label='" + label + '\'' +
                ", list=" + list +
                '}';
    }
}
