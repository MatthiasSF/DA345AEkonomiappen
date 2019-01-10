package com.example.ai0204.p1.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

/**
 * Interface used to get data from the database
 * @author Matthias Falk
 */
@Dao
public interface DatabaseAccess{

    @Insert
    void insertUser(User... users);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Insert
    void insertIncome(Income... income);

    @Query("SELECT * FROM income_table where UserId = :userId")
    List<Income> getAllIncome(int userId);

    @Insert
    void insertExpense(Expense... expenses);

    @Query("SELECT * FROM expense_table where UserId = :userId")
    List<Expense> getAllExpenses(int userId);

    @Query("SELECT Firstname FROM user_table where UserId = :userId")
    String getFirstname(int userId);

    @Query("SELECT Lastname FROM user_table where UserId = :userId")
    String getLastname(int userId);
}
