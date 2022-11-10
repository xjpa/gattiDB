package com.chadoverfl0w.gattiDB.raw;

import java.io.*;


public class DbFileHandler {
    private RandomAccessFile dbFile;
    public DbFileHandler(final String dbFileName) throws FileNotFoundException {
        this.dbFile = new RandomAccessFile(dbFileName, "rw");
    }
    //db for that buggati
    //todo: may refactor later to be a more generic db
    public boolean addData(String name,
                           int age,
                           String address,
                           String plateNumber,
                           String description) throws IOException {
        this.dbFile.seek(this.dbFile.length());
        //todo: refactor later to remove magic numbers
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
    public Owner readDbRow(int dbRowNumber) throws IOException {
        byte[] row = this.readRecordRawBytes(dbRowNumber);
        Owner owner = new Owner();
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(row));

        //reading stuff: name, age, address, plate #, description
        int nameLength = stream.readInt();
        byte[] byteArray = new byte[nameLength];
        owner.name = new String(byteArray);

        owner.age = stream.readInt();

        byteArray = new byte[stream.readInt()];
        stream.read(byteArray);
        owner.address = new String(byteArray);

        byteArray = new byte[stream.readInt()];
        stream.read(byteArray);
        owner.plateNumber = new String(byteArray);

        byteArray = new byte[stream.readInt()];
        stream.read(byteArray);
        owner.description = new String(byteArray);

        return owner;
    }
    //read the raw bytes
    private byte[] readRecordRawBytes(int dbRowNumber) throws IOException {
        this.dbFile.seek(0);
        if (this.dbFile.readBoolean())
            return new byte[0];
        this.dbFile.seek(dbRowNumber + 1);
        int recordLength = this.dbFile.readInt();
        byte[] data = new byte[recordLength];
        this.dbFile.read(data);
        return data;
    }

    public void closeDbFile() throws IOException {
        this.dbFile.close();
    }
}
