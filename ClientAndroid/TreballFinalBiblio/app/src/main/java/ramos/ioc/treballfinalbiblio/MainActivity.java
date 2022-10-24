package ramos.ioc.treballfinalbiblio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.util.Log;
import android.view.View;

import connection.ConnectionManager;
import model.LogInCredentials;
import model.LogInKeys;
import ramos.ioc.treballfinalbiblio.databinding.ActivityMainBinding;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private EditText userNameEditText;
    private ImageButton hideButton;
    private Button loginButton;
    private TextView signUpLink;
    private EditText passwordEditText;
    Intent signInIntent;
    LogInKeys logInKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userNameEditText = binding.content.logInUserNameEditText;
        hideButton = binding.content.hidePasswordButton;
        loginButton = binding.content.logInButton;
        passwordEditText = binding.content.logInPasswordEditText;
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        signUpLink = binding.content.signInLink;

        //Intents

        signInIntent = new Intent(this, SignInActivity.class);
        Toast toast = Toast.makeText(this, "Inici de sessió activat!", Toast.LENGTH_SHORT);

        //ASYNC TASK LOADER


        View.OnClickListener mainActivityListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CONNEXIONDEBUG", "You clicked the login button");
                //LOGIN
                if (view.getId() == loginButton.getId()) {
                    logInKeys = new LogInKeys(userNameEditText.getText().toString(), passwordEditText.getText().toString() );
                    if (logInKeys.getPassword() != null && logInKeys.getUserName() != null) {

                        //Credencials de prova
                        if (logInKeys.getUserName().equals("test") && logInKeys.getPassword().equals("test")) {
                            toast.show();
                        }else{
                            //Credencials de veritat
                            //TODO Login amb el servidor
                            if (!logInKeys.getPassword().isEmpty()&&!logInKeys.getUserName().isEmpty()){
                                //IMPLEMENTAR: connexió amb servidor
                                String token;

                                token = ConnectionManager.login(logInKeys);
                                if (token==null){
                                    token = "NOTOKEN";

                                }
                                Log.d("CONNEXIONDEBUG", token);

                            }
                        }

                    }
                }

                //LINK A SIGNUP
                if (view.getId() == signUpLink.getId()) {
                    startActivity(signInIntent);

                }


                if (view.getId() == hideButton.getId()) {
                    if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        //Resetejem el cursor
                        passwordEditText.setSelection(passwordEditText.getText().length());
                    } else {
                        //Resetejem el cursor

                        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        passwordEditText.setSelection(passwordEditText.getText().length());
                    }
                }
            }
        };

        //Add listener

        loginButton.setOnClickListener(mainActivityListener);
        hideButton.setOnClickListener(mainActivityListener);
        signUpLink.setOnClickListener(mainActivityListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



}

