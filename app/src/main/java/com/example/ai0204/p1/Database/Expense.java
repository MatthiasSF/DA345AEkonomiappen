package com.example.ai0204.p1.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Class that defines an Expense object and sets the up the Expensetable
 * @author Matthias Falk
 */
@Entity(tableName = "expense_table")
public class Expense {
    @NonNull
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = "Utgiftsid")
    private int expenseId;
    @NonNull
    @ColumnInfo(name="UserId")
    @ForeignKey(entity = User.class, parentColumns = "Användarid", childColumns = "Användarid")
    private int userId;
    @ColumnInfo(name = "Kategori")
    private String category;
    @ColumnInfo(name = "Datum")
    private String date;
    @ColumnInfo(name = "Titel")
    private String title;
    @ColumnInfo(name = "Belopp")
    private int amount;

    /**
     * Constructor that creates an Expense object
     * @param userId - foreignkey. Links the expense to the user that creates the expense
     * @param category - category of the expense
     * @param date - the date of the expense
     * @param title - the title of the expense
     * @param amount - the expense amount
     */
    public Expense (int userId, String category, String date, String title, int amount){
        this.userId = userId;
        this.category = category;
        this.date = date;
        this.title = title;
        this.amount = amount;
    }
    @NonNull
    public int getExpenseId() {
        return expenseId;
    }
    public String getCategory() {
        return category;
    }
    public String getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public int getAmount() {
        return amount;
    }
    public void setExpenseId(@NonNull int expenseId) {
        this.expenseId = expenseId;
    }
    @NonNull
    public int getUserId() {
        return userId;
    }

    /**
     * @return an string containing the date and the category
     */
    public String toStringFirst(){
        return date + " " + category;
    }

    /**
     * @return an String containing the date, category, title and the amount
     */
    public String toStringSecond(){
        return " Datum: " + date +"\nKategori:  " + category +  "\nTitel: " + title + "\nBelopp: " + String.valueOf(amount) + " :-";
    }
}
