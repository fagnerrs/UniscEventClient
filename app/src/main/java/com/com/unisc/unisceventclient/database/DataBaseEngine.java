package com.com.unisc.unisceventclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseEngine extends SQLiteOpenHelper
{
    private static String m_DatabaseName = "cliententerprise.db";
    private static int m_Version = 1;

    public DataBaseEngine(Context context) {
        super(context, m_DatabaseName, null, m_Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteTables(db);
        createTables(db);
    }

    public void createTables(SQLiteDatabase db)
    {

        db.execSQL(new StringBuilder().append("create table pessoa")
                .append("( id integer primary key, ")
                .append("  nome text,   ")
                .append("  email text,   ")
                .append("  matricula numeric )  ")
                .toString());

        db.execSQL(new StringBuilder().append("create table login")
                .append("( id integer primary key autoincrement, ")
                .append("  nome_usuario text,   ")
                .append("  pessoa_id integer,   ")
                .append("  senha text )  ")
                .toString());


        db.execSQL(new StringBuilder().append("create table presenca")
                .append("( id integer primary key autoincrement, ")
                .append("  evento_id numeric,   ")
                .append("  encontro_id numeric,   ")
                .append("  pessoa_id numeric,  ")
                .append("  entrada_saida integer,  ")
                .append("  data numeric )  ")
                .toString());

    }

    public void deleteTables(SQLiteDatabase db)
    {
        db.execSQL(new StringBuilder().append(" drop table if exists pessoa ")
                .toString());

        db.execSQL(new StringBuilder().append(" drop table if exists login ")
                .toString());


        db.execSQL(new StringBuilder().append(" drop table if exists presenca ")
                .toString());
    }
}
