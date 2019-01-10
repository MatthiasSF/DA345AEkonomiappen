package com.example.ai0204.p1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * Activity that displays all saved users. When an user is selected from the list PickedPersonAltActivity
 * is called via ActivityController.
 * @author Matthias Falk
 */
public class ExistingActivity extends AppCompatActivity {
    private ListView lView;
    private ExistingUserListAdapter existingUserListAdapter;
    private DBController dbController;
    private ActivityController acController;
    private Button deleteAllBtn;

    /**
     * basic onCreate method.
     * calls initialize().
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing);
        initialize();
    }

    /**
     * Method used to initialize the different components used in the Activity.
     * Gets the userid (Primary key) from the intent and forwards it to the Activitycontroller.
     */
    private void initialize() {
        this.lView = findViewById(R.id.Names);
        lView.setOnItemClickListener(new ItemListener());
        this.dbController = new DBController(this);
        this.acController = new ActivityController(this);
        acController.setUserId(getIntent().getIntExtra("Användarid", -1));
        this.existingUserListAdapter = new ExistingUserListAdapter(this,dbController.getUserList());
        deleteAllBtn = findViewById(R.id.deleteAllButton);
        deleteAllBtn.setOnClickListener(new DeleteAllListener());
        setAdapter(existingUserListAdapter);
    }

    /**
     * Sets the adapter for the listview.
     * @param adapter the adapter
     */
    public void setAdapter (ExistingUserListAdapter adapter){
        lView.setAdapter(adapter);
    }

    /**
     * Inner class that implements an itemclicklister.
     * Used by the Listview.
     */
    private class ItemListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            acController.setUserId(existingUserListAdapter.getUser(i).getUserId());
            acController.listExPersonClick();
        }
    }

    /**
     * Inner class with an clicklistener that delets all the saved users.
     * Used by the deleteAllBtn.
     */
    private class DeleteAllListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            dbController.deleteAllUsers();
            initialize();
        }
    }
}
