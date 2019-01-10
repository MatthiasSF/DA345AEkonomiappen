package com.example.ai0204.p1.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Class that defines an Income object and sets the up the Incometable
 * @author Matthias Falk
 */
@Entity(tableName = "income_table")
public class Income {
    @NonNull
    @PrimaryKey (autoGenerate=true)
    @ColumnInfo (name = "Inkomstid")
    private int incomeId;
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
     * Constructor that creates an Income object
     * @param userId - foreignkey. Links the income to the user that creates the income
     * @param category - category of the income
     * @param date - the date of the income
     * @param title - the title of the income
     * @param amount - the income amount
     */
    public Income (int userId, String category, String date, String title, int amount){
        this.userId = userId;
        this.category = category;
        this.date = date;
        this.title = title;
        this.amount = amount;
    }
    @NonNull
    public int getIncomeId() {
        return incomeId;
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
    public void setIncomeId(@NonNull int incomeId) {
        this.incomeId = incomeId;
    }
    @NonNull
    public int getUserId() {
        return userId;
    }

    /**
     * @return an string containing the date and the category
     */
    public String toStringFirst(){
        return date + " " + title;
    }

    /**
     * @return an String containing the date, category, title and the amount
     */
    public String toStringSecond(){
        return " Datum: " + date +"\nKategori:  " + category +  "\nTitel: " + title + "\nBelopp: " + String.valueOf(amount) + " :-";
    }
}
