package com.chadoverfl0w.gattiDB.testapplication;

import com.chadoverfl0w.gattiDB.raw.DbFileHandler;

public class TestApplication {
    public static void main(String[] args) {
        try {
            DbFileHandler dbFh = new DbFileHandler("test.db");
            dbFh.addData("GigaChad", 20, "Toronto", "ABC-123", "what color is your bugatti");
            dbFh.closeDbFile();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
