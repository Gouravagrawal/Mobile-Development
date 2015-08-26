package com.example.a;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class LoginActivity extends ActionBarActivity  {

	private CallbackManager callbackManager;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // setting default screen to login.xml
	        setContentView(R.layout.login);
	        
	        getSupportActionBar().hide();
	 
	        TextView registerScreen = (TextView) findViewById(R.id.btnSignup);
	        
	        Button mainScreen= (Button)findViewById(R.id.btnLogin);
	        
	        Button fblogin= (Button)findViewById(R.id.btnLoginfb);
	        
	        
	        fblogin.setOnClickListener(new View.OnClickListener() {
	       	 
	            public void onClick(View v) {
	            	initFacebookCallbacks();   
	            	LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
	            }
	        });
	        
	        
	        mainScreen.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View v) {
	                // Switching to Register screen
	                Intent i = new Intent(getApplicationContext(), MainActivity.class);
	                startActivity(i);
	            }
	        });
	        	 
	 }	 
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	 super.onActivityResult(requestCode, resultCode, data);
	 if(callbackManager!=null){
	 callbackManager.onActivityResult(requestCode, resultCode, data);
	   }
	 }
	 
	 
	 private void initFacebookCallbacks() {
		  FacebookSdk.sdkInitialize(this.getApplicationContext());
		  callbackManager = CallbackManager.Factory.create();
		  LoginManager.getInstance().registerCallback(callbackManager,
		    new FacebookCallback<LoginResult>() {
		   @Override
		   public void onSuccess(LoginResult loginResult) {
		    Log.d("Success", "Login");
		    if (AccessToken.getCurrentAccessToken() != null) {
		     /*Good approach but not provide email*/       
		      Profile profile = Profile.getCurrentProfile();
		      if(profile != null){
		      Log.d("Login", "getId "+profile.getId());
		      Log.d("Login",
		      "getFirstName "+profile.getFirstName());
		      Log.d("Login",
		      "getMiddleName "+profile.getMiddleName());
		      Log.d("Login",
		      "getLastName "+profile.getLastName());
		      Log.d("Login", "getName "+profile.getName());
		      }

		     /*Other approach to get email as well but not provide
		         image, using graph request*/
		     GraphRequest request = GraphRequest.newMeRequest(
		       loginResult.getAccessToken(),
		       new GraphRequest.GraphJSONObjectCallback() {
		        @Override
		        public void onCompleted(
		          JSONObject object,
		          GraphResponse response) {

		         Profile profile = Profile.getCurrentProfile();
		         //profile.getProfilePictureUri(width, height);
		        
		         // Application code
		         Log.d("Login",
		           "facebook response== "
		             + object.toString());
		         
		         Toast.makeText(LoginActivity.this, "Loginn Success "+object.toString(),
		    		      Toast.LENGTH_LONG).show();
		          
		          Intent i = new Intent(getApplicationContext(), MainActivity.class);
	                startActivity(i);
		         
		        }
		       });
		     request.executeAsync();

		    }
		   }

		   @Override
		   public void onCancel() {
		    Toast.makeText(LoginActivity.this, "Login Cancel",
		      Toast.LENGTH_LONG).show();
		   }

		   @Override
		   public void onError(FacebookException exception) {
		    Toast.makeText(LoginActivity.this, exception.getMessage(),
		      Toast.LENGTH_LONG).show();
		   }
		  });
		 }
}
