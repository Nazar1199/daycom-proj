package com.example.daycom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "repair")
public class repair{
    @PrimaryKey(autoGenerate = true)
    private int Id;

    @ColumnInfo(name = "Code")
    private int Code;

    @ColumnInfo(name = "Surname")
    private String Surname;

    @ColumnInfo(name = "Status")
    private int Status;

    public void setId(int id){Id=id;}
    public int getId() {
        return Id;
    }
    public int getStatus(){
        return Status;
    }
    public void setStatus(int st){
        Status=st;
    }
    public String getSurname(){
        return Surname;
    }
    public void setSurname(String sn){
        Surname=sn;
    }
    public int getCode(){ return Code; }
    public void setCode(int cd){
        Code=cd;
    }
}

