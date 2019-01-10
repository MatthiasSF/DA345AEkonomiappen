package com.example.ai0204.p1;

import android.content.Context;
import com.example.ai0204.p1.Database.Expense;
import com.example.ai0204.p1.Database.Income;
import com.example.ai0204.p1.Database.User;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Controller class that handles communication with the database.
 * @author Matthias Falk
 */
public class DBController {
    private Context context;
    private DBAccess dbAccess;
    private List<User> userList;
    private List<Income> incomeList;
    private List<Expense> expenseList;
    private int userId;
    private String name;

    /**
     * Constructer for the controller.
     * Calls the methods initializeDatabase and getUsers
     * @param context
     */
    public DBController(Context context){
        this.context = context;
        initializeDatabase();
        getUsers();
    }

    /**
     * Starts up the database
     */
    private void initializeDatabase() {
        dbAccess = new DBAccess(context);
    }

    /**
     * Gets all the users in the database.
     */
    private void getUsers() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                userList = dbAccess.getAllUsers();
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the saved income posts from the database.
     * @param userId Gets Income posts that is saved on this userId.
     * @return incomeList - the list with all the received Incomes.
     */
    public List<Income> getIncome(final int userId){
        Thread t = new Thread(new Runnable() {
            public void run() {
                incomeList = dbAccess.getAllIncome(userId);
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return incomeList;
    }

    /**
     * Gets all the saved Expense posts from the database.
     * @param userId Gets Expense posts that is saved on this userId
     * @return expenseList - the list with all the received Expenses.
     */
    public List <Expense> getExpense(final int userId){
        this.userId = userId;
        Thread t = new Thread(new Runnable() {
            public void run() {
                expenseList = dbAccess.getAllExpenses(userId);
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return expenseList;
    }

    /**
     * @return userList - List with all users
     */
    public List<User> getUserList(){
        return this.userList;
    }

    /**
     * Method used to add an new user to the database.
     * @param firstName - the users firstname.
     * @param lastName - the users lastname.
     */
    public void addUser(final String firstName, final String lastName){
        Thread t = new Thread(new Runnable() {
            public void run() {
                User newUser = new User(firstName , lastName);
                dbAccess.insertUser(newUser);
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to add an new Income to the database.
     * @param userId - the username that the income will be added to.
     * @param category - the category of the income
     * @param date - the date of the income
     * @param title - the title of the income
     * @param amount - the amount of the income
     */
    public void addIncome(final int userId, final String category, final String date, final String title, final int amount){
        this.userId = userId;
        Thread t = new Thread(new Runnable() {
            public void run() {
                Income newIncome = new Income(userId, category, date, title, amount);
                dbAccess.insertIncome(newIncome);
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deletes all the users from the database
     */
    public void deleteAllUsers(){
        Thread t = new Thread(new Runnable() {
            public void run() {
                dbAccess.deleteAllUsers();
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to add an new Expense
     * @param userId - the username that the expense will be added to.
     * @param category - the category of the expense
     * @param date - the date of the expense
     * @param expenseTitle - the title of the expense
     * @param amount - the amount of the expense
     */
    public void addExpense(final int userId, final String category, final String date, final String expenseTitle, final int amount) {
        this.userId = userId;
        Thread t = new Thread(new Runnable() {
            public void run() {
                Expense newExpense = new Expense(userId, category, date, expenseTitle, amount);
                dbAccess.insertExpense(newExpense);
            }
        });
        t.start();
        try{
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns the name that is saved under the currently active primarykey userId
     * Collaborates with the method getNamesFromDb
     * @return name
     */
    public String getName(){
        getNamesFromDb();
        return name;
    }

    /**
     * Method that gets a name from the database that is saved under the currently active primarykey userId
     * Collaborates with the method getName
     */
    public void getNamesFromDb() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> result = es.submit(new Callable<String>() {
            public String call() throws Exception {
                String firstName = dbAccess.getFirstname(userId);
                String lastName = dbAccess.getLastname(userId);
                return firstName + " " + lastName;
            }
        });
        try {
            name = result.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}
