package com.example.ai0204.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.ai0204.p1.Database.User;
import java.util.List;

/**
 * Adapter that extends ArrayAdapter for the list contained in the activity ExistingActivity
 * @author Matthias Falk
 */
public class ExistingUserListAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private List<User> userList;

    /**
     * Constructor for adapter. Gets an Context and an userlist
     * @param context Targeted Context
     * @param userList List containing all the created users
     */
    public ExistingUserListAdapter(Context context, List<User> userList) {
        super(context, android.R.layout.simple_list_item_1,userList);
        this.userList = userList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * method that overrides the getView method in the class ArrayAdapter.
     * Populates the listview with the users from the userlist
     * @param position
     * @param convertView
     * @param parent
     * @return tv - The textview that gets inserted into the ListView
     */
    public View getView(int position, View convertView, ViewGroup parent){
        TextView tv;
        if (convertView == null){
            tv = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        else{
            tv = (TextView) convertView;
        }
            tv.setText(userList.get(position).toString());
        return tv;
    }

    /**
     * Returns the user from the userList at position
     * @param position
     * @return the user at position
     */
    public User getUser(int position){
        return userList.get(position);
    }
}