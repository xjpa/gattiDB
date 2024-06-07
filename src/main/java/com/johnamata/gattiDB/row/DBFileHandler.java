package com.johnamata.gattiDB.row;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DBFileHandler {
    private RandomAccessFile dbFile;
    public DBFileHandler(final String dbFileName) throws FileNotFoundException {
        this.dbFile =  new RandomAccessFile(dbFileName, "rw");
    }
    public void closeDbFile() throws IOException {
        this.dbFile.close();
    }
}
