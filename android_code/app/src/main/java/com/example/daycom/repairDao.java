package com.example.daycom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface repairDao{

    @Query("SELECT * FROM repair WHERE id = :Id")
    repair getRepairById(int Id);

    @Query("SELECT * FROM repair WHERE Code = :Code")
    repair getRepairByCode(int Code);

    @Query("SELECT Surname FROM repair WHERE Code = :Code")
    String getSurnameByCode(int Code);

    @Query("SELECT Status FROM repair WHERE Code = :Code AND Surname = :Surname")
    int getStatusBySNameANDCode(int Code, String Surname);

    @Insert
    void insert(repair r);

}
