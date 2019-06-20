package com.os.paytzwakal.reg.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RegistrationDao {

    @Query("SELECT * FROM registartiondata ORDER BY created DESC")
    List<RegistartionData> getAllData();

    @Insert
    void insert(RegistartionData... registartionData);

    @Update
    void update(RegistartionData... registartionData);

    @Query("DELETE FROM registartiondata")
    void delete();
}
