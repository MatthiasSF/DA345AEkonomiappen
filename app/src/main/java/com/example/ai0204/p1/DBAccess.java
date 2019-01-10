package com.example.ai0204.p1;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.ai0204.p1.Database.DatabaseAccess;
import com.example.ai0204.p1.Database.Expense;
import com.example.ai0204.p1.Database.Income;
import com.example.ai0204.p1.Database.P1_Matthias;
import com.example.ai0204.p1.Database.User;
import java.util.List;

/**
 * Class used to get access to the database.
 * @author Matthias Falk
 */
public class DBAccess {
    private static final String DATABASE_Name = "P1_Matthias";
    private P1_Matthias database;
    private DatabaseAccess p1MatthiasAccess;

    /**
     * Constructor for the class.
     * @param context
     */
    public DBAccess(Context context){
        database = Room.databaseBuilder(context,
                P1_Matthias.class,
                DATABASE_Name)
                .fallbackToDestructiveMigration()
                .build();
        p1MatthiasAccess = database.databaseAccess();
    }

    /**
     * Inserts an user object into the database.
     * @param user
     */
    public void insertUser(User... user){

       p1MatthiasAccess.insertUser(user);
    }

    /**
     * Deletes all users from the database.
     */
    public void deleteAllUsers(){
        p1MatthiasAccess.deleteAllUsers();
    }

    /**
     * @return List - all the saved users in the database.
     */
    public List<User> getAllUsers(){
        return p1MatthiasAccess.getAllUsers();
    }

    /**
     * Inserts an Income object into the database.
     * @param income
     */
    public void insertIncome(Income... income){
        p1MatthiasAccess.insertIncome(income);
    }

    /**
     * @param userId foreignkey for Income.
     * @return List - all incomes saved under userId.
     */
    public List<Income> getAllIncome(int userId){
        return p1MatthiasAccess.getAllIncome(userId);
    }

    /**
     * Inserts an Expense object into the database.
     * @param expenses
     */
    public void insertExpense(Expense... expenses){
        p1MatthiasAccess.insertExpense(expenses);
    }

    /**
     * @param userId foreing key for Expense.
     * @return List - all expenses saved under userId.
     */
    public List<Expense> getAllExpenses(int userId){
        return p1MatthiasAccess.getAllExpenses(userId);
    }

    /**
     * @param userId primarykey in User.
     * @return String - the firstname off the saved user under userId.
     */
    public String getFirstname(int userId){
        return p1MatthiasAccess.getFirstname(userId);

    }

    /**
     * @param userId primarykey in User.
     * @return String - the lastname off the saved user under userId.
     */
    public String getLastname(int userId){
        return p1MatthiasAccess.getLastname(userId);
    }
}
