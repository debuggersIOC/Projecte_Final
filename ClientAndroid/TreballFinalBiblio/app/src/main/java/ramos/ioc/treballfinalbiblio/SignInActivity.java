package ramos.ioc.treballfinalbiblio;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import connection.ConnectionChecker;
import connection.ConnectionManager;
import connection.UserSender;
import model.User;
import ramos.ioc.treballfinalbiblio.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {


    private ActivitySignInBinding binding;

    private Button signUpButton;

    //TextViews
    private TextView loginLink;

    private TextView errorMessageTextView;

    //EditText
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private EditText idEditText;


    //TODO TEST EDIT TEXT: Esborrar mes endavant
    private EditText ipProvaEditText;
    private EditText portProvaEditText;

    private User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //@TODO: substituir els findview per binding quan averigui per que no fuciona binding.
        // Es pot deixar amb findview en ser tants pocs, pero de cada les actualitzacions caldra fer servir binding.
        //findViewById
        loginLink = findViewById(R.id.login_link_textView);
        signUpButton = findViewById(R.id.sign_up_button);

        userNameEditText = findViewById(R.id.logIn_userName_editText);
        passwordEditText = findViewById(R.id.logIn_password_editText);
        repeatPasswordEditText = findViewById(R.id.logIn_passwordRepeat_editText);
        idEditText = findViewById(R.id.logIn_id_editText);
        errorMessageTextView = findViewById(R.id.error_repeatPassword_textView);

        //TODO CAPTA ELS TEXTS VIEW : ESBORRAR MES TARD
        ipProvaEditText = findViewById(R.id.test_ip_editText);
        portProvaEditText = findViewById(R.id.test_port_editText);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Intent intentUserProfile = new Intent(this, UserProfileScreen.class);
        //Aquesta flag fa que la activity neteji totes les activities del mateix tipus en el stack.


        View.OnClickListener listenerSignInActivity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == loginLink.getId()) {
                    startActivity(intent);

                }

                if (view.getId() == signUpButton.getId()) {
                    errorMessageTextView.setVisibility(View.INVISIBLE);
                    String userPasswordCredential = passwordEditText.getText().toString();
                    String userPasswordRepetition = repeatPasswordEditText.getText().toString();


                    //Comprovem que els camps estan plens
                    if (userPasswordCredential.isEmpty() || userPasswordRepetition.isEmpty()) {
                        errorMessageTextView.setText("Aquest camp es obligatori");
                        errorMessageTextView.setVisibility(View.VISIBLE);

                    } else {
                        //Comprovem que les contrasenyes introduïdes son les mateixes.
                        if (userPasswordCredential.equals(userPasswordRepetition)) {
                            //TODO COMPROVAR QUE TOTS ELS CAMPS ESTAN PLENS

                            String userIdCredential = idEditText.getText().toString();
                            String userNameCredential = userNameEditText.getText().toString();
                            String userType = "0";

                            newUser = new User(userIdCredential, userNameCredential, userPasswordCredential, userType);
                            Log.d("CONNECTIONDEBUG", newUser.getId());

                            //COMPTE DE PROVA. User: Test, Password: Test - Porta a un profile "fals" de mostra.

                            if (newUser.getUsuari().equals("test") && newUser.getPassword().equals("test")) {
                                //Creem un intent afegint de dades el new user
                                //TODO CANVIAR KEY PER NOM MILLOR
                                intentUserProfile.putExtra("userProfile", newUser);
                                startActivity(intentUserProfile);

                            } else {
                                //ENREGISTRAMENT D'USUARI
                                //Comprova la connexió
                                ExecutorService executor = Executors.newFixedThreadPool(2);
                                ConnectionChecker connCheck = new ConnectionChecker(SignInActivity.this, ConnectionManager.SERVER_IP, ConnectionManager.SERVER_PORT);
                                //TODO Comprova la connexió. Caldra preparar una peticio especial al servidor (?).
                                // o be una connexió que no espatlli la connexió (en un altre fil quan el server sigui multifil(?))
//                                Future future = executor.submit(connCheck);
//                                boolean connCheckResult=false;
//                                try {
//                                    connCheckResult = (boolean)future.get();
//                                } catch (ExecutionException e) {
//                                    e.printStackTrace();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }

//                                if (connCheckResult){
//                                    Toast toast = Toast.makeText(SignInActivity.this, "Connexion ON", Toast.LENGTH_SHORT);
//                                    toast.show();
//
//                                    /
//
//                                } else {
//                                    Toast toast = Toast.makeText(SignInActivity.this, "Connexion OFF", Toast.LENGTH_SHORT);
//                                    toast.show();
//                                }

                                //Enregistra l'usuari
                                UserSender userSender = new UserSender(newUser);
                                executor.execute(userSender);
                                Log.d("CONNECTIONDEBUG", "acaba run user sender");
                            }
                        } else {
                            //Si les constrasenyes no son les mateixes mostrem missatge d'error
                            Log.d("SignIn",
                                    "Les contrasenyes no son la mateixa");
                            errorMessageTextView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        };

        //Set listeners

        loginLink.setOnClickListener(listenerSignInActivity);
        signUpButton.setOnClickListener(listenerSignInActivity);

    }

    @Override
    protected void onPause() {
        super.onPause();
        ConnectionManager.closeSocket();
        Log.d("CONNECTIONDEBUG", "socke tTancat");
    }
}