package com.example.mvp.model;

public class Resourse<Song> {
    /*{
    "code": 1,
    "data": {
        "name": "孤雏",
        "url": "http://music.163.com/song/media/outer/url?id=421486605",
        "picurl": "http://p1.music.126.net/1plA3qURt0VPElgnaTRg2w==/18302470556355853.jpg",
        "artistsname": "AGA"
    }
}*/
    private String code;
    private Song data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Song getData() {
        return data;
    }

    public void setData(Song data) {
        this.data = data;
    }
}
