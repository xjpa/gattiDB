package com.chadoverfl0w.gattiDB.raw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DbFileHandler {
    private RandomAccessFile dbFile;
    public DbFileHandler(final String dbFileName) throws FileNotFoundException {
        this.dbFile = new RandomAccessFile(dbFileName, "rw");
    }
    //db for that buggati
    //todo: may refactor later to be more generic?
    public boolean addData(String name,
                           int age,
                           String address,
                           String plateNumber,
                           String description) throws IOException {
        this.dbFile.seek(this.dbFile.length());
        int recordLength =  4 +
                name.length() +
                4 +
                4 +
                address.length() +
                4 +
                plateNumber.length() +
                4 +
                description.length();

        this.dbFile.writeBoolean(false);

        this.dbFile.writeInt(recordLength);

        this.dbFile.writeInt(name.length());
        this.dbFile.write(name.getBytes());

        this.dbFile.writeInt(age);

        this.dbFile.writeInt(address.length());
        this.dbFile.write(address.getBytes());

        this.dbFile.writeInt(plateNumber.length());
        this.dbFile.write(plateNumber.getBytes());

        this.dbFile.writeInt(description.length());
        this.dbFile.write(description.getBytes());

        return true;
    }
    public void closeDbFile() throws IOException {
        this.dbFile.close();
    }
}
