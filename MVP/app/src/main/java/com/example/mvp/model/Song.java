package com.example.mvp.model;

public class Song {
    /* "name": "孤雏",
        "url": "http://music.163.com/song/media/outer/url?id=421486605",
        "picurl": "http://p1.music.126.net/1plA3qURt0VPElgnaTRg2w==/18302470556355853.jpg",
        "artistsname": "AGA"*/
    private String name;
    private String url;
    private String artistsname;
    private String picurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtistsname() {
        return artistsname;
    }

    public void setArtistsname(String artistsname) {
        this.artistsname = artistsname;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
