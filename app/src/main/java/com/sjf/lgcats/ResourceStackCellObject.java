package com.sjf.lgcats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shomil on 6/1/17.
 */

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
