package com.chadoverfl0w.raw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DbFileHandler {
    private RandomAccessFile dbFile;
    public DbFileHandler(final String dbFileName) throws FileNotFoundException {
        this.dbFile = new RandomAccessFile(dbFileName, "rw");
    }
    public void closeDbFile() throws IOException {
        this.dbFile.close();
    }
}
