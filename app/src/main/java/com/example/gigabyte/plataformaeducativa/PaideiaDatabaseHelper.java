package com.example.gigabyte.plataformaeducativa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PaideiaDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "paideia";
    private static final int DB_VERSION = 1;

    public PaideiaDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase (sqLiteDatabase, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateMyDatabase (sqLiteDatabase, oldVersion, newVersion);

    }

    private void updateMyDatabase (SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        if (oldVersion < 1){
            sqLiteDatabase.execSQL("CREATE TABLE MATERIA (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertMateria(sqLiteDatabase, "Español", "ninguna", R.drawable.espanol);
            insertMateria(sqLiteDatabase, "Matemáticas", "ninguna", R.drawable.matem);

        }

    }

    private static void insertMateria(SQLiteDatabase sqLiteDatabase, String name, String description, int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        sqLiteDatabase.insert("MATERIA", null, drinkValues);
    }
}
