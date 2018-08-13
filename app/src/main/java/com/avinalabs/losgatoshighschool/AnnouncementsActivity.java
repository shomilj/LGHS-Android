//
// AnnouncementsActivity.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// AnnouncementsActivity - holds a list of daily announcements
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AnnouncementsActivity extends AppCompatActivity {

    // Holds a mList of Announcement objects
    private ArrayList<Announcement> mList;

    // The announcements ListView
    private ListView mListView;

    // called when the view is created
    // pre: none
    // post: configures the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        assignVariables();
        startDownload();
    }

    // pre: none
    // post: starts a download process in a background thread to download the announcements
    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchAnnouncements();
            }
        }).start();
    }

    // pre: none
    // post: initializes private variables
    private void assignVariables() {
        mList = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.announcement_list_view);
    }

    // pre: none
    // post: sets up the view
    private void setupView() {
        // links this class to the layout XML file
        setContentView(R.layout.activity_announcements);

        // configures toolbar back button & title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Daily Announcements");
    }

    // pre: mListView is initialized
    // post: assigns a listener to the ListView
    private void setupListListener() {
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tappedAnnouncement(position, context);
            }
        });
    }

    // called when the user taps on a item in the list
    // pre: position is a valid index of mList
    // post: passes the announcement information to the next screen and moves there
    private void tappedAnnouncement(int position, Context context) {
        Announcement a = mList.get(position);
        Intent detailIntent = new Intent(context, AnnouncementDetailActivity.class);
        detailIntent.putExtra("date", a.getDate());
        detailIntent.putExtra("content", a.getContent());
        startActivity(detailIntent);
    }

    // pre: none
    // post: fetches the announcements from the URL & parses them into list
    private void fetchAnnouncements() {

        // get the link associated with the announcement spreadsheet
        String link = LinkUtils.getLink(LinkUtils.Backend.dailyAnnouncementsFeed, getApplicationContext());

        // download the contents of the text file at the link
        String file = StringUtil.getUrlContents(link);

        RssParser parser = new RssParser(file);
        List<RssParser.Item> rows = parser.items;

        // ignore first row bc of header
        // start at index i = 1 and go to number of rows
        for (RssParser.Item item:rows) {
                // create a new announcement & add it if it's upcoming
                Announcement a = new Announcement(item.title, item.description);
                mList.add(a);

        }

        // check that colleges have parsed correctly
        for (Announcement a : mList) {
            System.out.println(a);
        }

        // update the list view in the main thread
        runOnUiThread(new Runnable() {
            public void run() {
                fillListView();
                setupListListener();
            }
        });
    }

    // pre: none
    // post: fills the list view with list items
    private void fillListView() {

        // create an array of list items (just the date)
        String[] listItems = new String[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            Announcement a = mList.get(i);
            listItems[i] = a.getDate();
        }

        // make the list view show that array
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }



    class RssParser extends DefaultHandler {

        private StringBuilder   content;
        private boolean         inItem;

        ArrayList<Item> items   = new ArrayList<Item>();
        private Item            lastItem;


        public RssParser(String url) {
            try {
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                //URL sourceUrl = new URL(url);
                xr.setContentHandler(this);
                xr.parse(new InputSource(new StringReader(url)));
            }
            catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            catch (SAXException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        class Item {
             String title;
             String description;
             String pubDate;
        }


        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {


            if (localName.equalsIgnoreCase("item")) {
                lastItem = new Item();
                items.add(lastItem);
                inItem = true;
            }

            content = new StringBuilder();
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            if (localName.equalsIgnoreCase("item")) {
                inItem = false;
            }

            if (localName.equalsIgnoreCase("title") && qName.equals("title")) {
                if (content == null) {
                    return;
                }

                if (inItem) {
                    lastItem.title = content.toString();
                }

                content = null;
            }

            if (localName.equals("encoded") && qName.equals("content:encoded")) {
                if (content == null) {
                    return;
                }

                if (inItem) {
                    lastItem.description = content.toString();
                }

                content = null;
            }

            if (localName.equalsIgnoreCase("pubDate")) {
                if (content == null) {
                    return;
                }

                if (inItem) {
                    lastItem.pubDate = content.toString();
                }

                content = null;
            }
         }


        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (content == null) {
                return;
            }

            content.append(ch, start, length);
        }


        public Item getItem(int index) {
            return items.get(index);
        }
    }
}
