package com.example.ai0204.p1.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Database
 * @Matthias Falk
 */

@Database (entities = {User.class, Income.class, Expense.class}, version = 9, exportSchema = false)
public abstract class P1_Matthias extends RoomDatabase {
    public abstract DatabaseAccess databaseAccess();
}
