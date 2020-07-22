package com.example.android.demobrowser;

public class Item {
    private int mImageResource;
    private String mLinkResource;
    private String mTitle;

    public Item(int mImageResource, String mLinkResource, String mTitle) {
        this.mImageResource = mImageResource;
        this.mLinkResource = mLinkResource;
        this.mTitle = mTitle;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmLinkResource() {
        return mLinkResource;
    }

    public String getmTitle() {
        return mTitle;
    }
    public void changetext1(String text){
        mTitle = text;
    }
}
