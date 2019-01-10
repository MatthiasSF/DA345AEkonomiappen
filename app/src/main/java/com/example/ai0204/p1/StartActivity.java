package com.example.ai0204.p1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The first activity that starts
 * Contains two buttons that starts up two different activitys depending on which one that is selected
 * newPersonBtn starts up NewPersonActivity
 * existingPersonBtn starts up ExistingActivity
 * @author Matthias Falk
 */
public class StartActivity extends AppCompatActivity {
    private Button newPersonBtn;
    private Button existingPersonBtn;
    private ActivityController acController;

    /**
     * basic onCreate method
     * calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
    }

    /**
     * Initializes the components and starts up the ActivityController
     */
    private void initialize(){
        this.newPersonBtn = findViewById(R.id.newPersonBtn);
        this.existingPersonBtn = findViewById(R.id.existingPersonBtn);
        acController = new ActivityController(this);
        setListeners();
    }

    /**
     * Method for setting buttonlisteners
     */
    private void setListeners(){
        newPersonBtn.setOnClickListener(new NewPersonListener());
        existingPersonBtn.setOnClickListener(new ExistingPersonListener());
    }

    /**
     * Inner class that implements an listener. Used by newPersonBtn
     */
    private class NewPersonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            acController.newPersonClick();
        }
    }

    /**
     * Inner class that implements an listener. Used by existingPersonBtn
     */
    private class ExistingPersonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            acController.exPersonClick();
        }
    }
}
