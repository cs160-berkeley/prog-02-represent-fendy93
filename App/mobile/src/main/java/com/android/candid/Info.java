package com.android.candid;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fendyzhou on 3/7/16.
 */
public class Info {
    private String name;
    private String party;
    private int img;
    private int web;
    private int email;
    private String moreInfo;
    private String tweet;
    private String endDate;
    private ArrayList<String> activeComittee;
    private ArrayList<String> recentBills;

    public Info(String name, String party, int img, int web, int email, String moreInfo, String tweet, String endDate, ArrayList<String> activeComittee, ArrayList<String> recentBills) {
        super();
        this.name = name;
        this.party = party;
        this.img = img;
        this.web = web;
        this.email = email;
        this.moreInfo = moreInfo;
        this.tweet =tweet;
        this.endDate = endDate;
        this.activeComittee = activeComittee;
        this.recentBills = recentBills;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getImg() {
        return img;
    }

    public int getWeb() {
        return web;
    }

    public int getEmail() {
        return email;
    }

    public String getmoreInfo() {
        return moreInfo;
    }

    public String getTweet() {
        return tweet;
    }

    public String getEndDate() {
        return endDate;
    }

    public ArrayList<String> getActiveComittee() {
        return activeComittee;
    }

    public ArrayList<String> getRecentBills() {
        return recentBills;
    }
}


