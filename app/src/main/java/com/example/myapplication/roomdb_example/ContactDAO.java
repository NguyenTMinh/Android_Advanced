package com.example.myapplication.roomdb_example;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public void addContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    @Query("SELECT * FROM contact")
    public List<Contact> getAllContacts();

    @Query("SELECT * FROM contact WHERE id == :id")
    public Contact getContact(int id);
}
