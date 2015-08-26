package com.example.a;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends ActionBarActivity{

	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // Set View to register.xml
	        setContentView(R.layout.register);
	        
	        getSupportActionBar().hide();
	 
	        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
	 
	        // Listening to Login Screen link
	        loginScreen.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View arg0) {
	                                // Closing registration screen
	                // Switching to Login Screen/closing register screen
	                finish();
	            }
	        });
	    }
}
