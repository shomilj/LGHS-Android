//
// ResourcesFragment.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// ResourcesFragment - fragment for the resources home screen
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourcesFragment extends Fragment {

    private ArrayList<ArrayList<String>> allResourcesList;

    private ListView listView;

    ArrayList items;

    public ResourcesFragment () {
        // Required empty public constructor

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResourcesFragment.
     */
    public static ResourcesFragment newInstance() {
        return new ResourcesFragment();
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resources, container, false);

        listView = (ListView) view.findViewById(R.id.resources_list_view);
        this.getActivity().setTitle("Resources");
        fillLinkMaps();

        int i = 1;
        ArrayList<String> row;
        items = new ArrayList();
        String header = null;
        HashMap<String, String> lines = null;

        while (i < allResourcesList.size()) {
            row = allResourcesList.get(i);
            if (row.size() != 3 || row.contains("")) {
                i += 1;
                continue;
            }
             try {
                URL url = new URL(row.get(1));
                //lines.
                i += 1;
                while (i < allResourcesList.size())
                {
                    lines.put(row.get(0), row.get(1));
                    row = allResourcesList.get(i);
                    url = new URL(row.get(1));
                    i += 1;
                }
            }
            catch (Exception e)
            {
                if (lines != null && lines.size() > 0 && row.size() == 3)
                {
                    items.add(new ResourceCellObject(header, lines));
                    header = null;
                    lines = null;
                }
                if (row.size() != 3 || row.contains("")) {
                    i += 1;
                    continue;
                }
                //header.
                header = row.get(0);
                lines = new HashMap<String, String>(10);
                i += 1;
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object object = items.get(position);
                if (object instanceof String) {
                    System.out.println("Header pressed!");
                } else if (object instanceof ResourceCellObject) {
                    Intent detailIntent = new Intent(getContext(), ResourcesListActivity.class);
                    detailIntent.putExtra("Map", ((ResourceCellObject) (object)).getLinkContent());
                    detailIntent.putExtra("HeaderSuffix", ((ResourceCellObject)object).getLabel());
                    startActivity(detailIntent);
                } else if (object instanceof ResourceStackCellObject) {
                    Intent detailIntent = new Intent(getContext(), ResourcesStackActivity.class);
                    detailIntent.putExtra("Stack", ((ResourceStackCellObject) (object)).getList());
                    startActivity(detailIntent);
                }
            }
        });

        listView.setAdapter(new ResourcesAdapter(this.getContext(), items));

        return view;
    }

    private void fillLinkMaps() {
        if (allResourcesList == null) {
            allResourcesList = FileUtil.parseTSV(FileUtil.readFromFile(FileUtil.FILE_RESOURCES, getContext()));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
