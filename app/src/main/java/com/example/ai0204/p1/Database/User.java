package com.example.ai0204.p1.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Class that defines an user object and sets the up the Usertable
 * @author Matthias Falk
 */
@Entity(tableName = "user_table")
public class User {
    @NonNull
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo (name = "UserId")
    private int userId;
    @ColumnInfo(name = "Firstname")
    private String firstName;
    @ColumnInfo(name = "Lastname")
    private String lastName;

    /**
     * Constructor that creates an user object
     * @param firstName
     * @param lastName
     */
    public User (String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @NonNull
    public int getUserId() {
        return userId;
    }
    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    /**
     * @return an String containing the firstname and lastname
     */
    public String toString(){
        return firstName + " " + lastName;
    }

}
