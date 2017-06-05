package com.sjf.lgcats;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by shomil on 6/1/17.
 */

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
