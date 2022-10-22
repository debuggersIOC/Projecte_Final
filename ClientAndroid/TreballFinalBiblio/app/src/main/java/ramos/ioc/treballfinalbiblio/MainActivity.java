package ramos.ioc.treballfinalbiblio;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.text.InputType;
import android.util.Log;
import android.view.View;

import connection.ConnectionUtils;
import model.LogInCredentials;
import ramos.ioc.treballfinalbiblio.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
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
    LogInCredentials logInCredentials;

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

                //LOGIN
                if (view.getId() == loginButton.getId()) {
                    logInCredentials = new LogInCredentials(userNameEditText.getText().toString(), passwordEditText.getText().toString() );
                    if (logInCredentials.getPassword() != null && logInCredentials.getUsername() != null) {

                        //Credencials de prova
                        if (logInCredentials.getUsername().equals("test") && logInCredentials.getPassword().equals("test")) {
                            toast.show();
                        }

                        //Credencials de veritat
                        //TODO Login amb el servidor
                        if (/**connexioLogin()**/false){
                            //IMPLEMENTAR: connexió amb servidor
                            Log.d("TODOSTUFF", "IMPLEMENTAR LOGIN AMB EL SERVIDOR");
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




    /**
    static public boolean isURLReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("http://google.com");   // Change to "http://google.com" for www  test.
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(10 * 1000);          // 10 s.
                urlc.connect();
                if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                    Log.wtf("Connection", "Success !");
                    return true;
                } else {
                    return false;
                }
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
     **/
/**
    public static boolean isHostReachable(String serverAddress, int serverTCPport, int timeoutMS){
        boolean connected = false;
        Socket socket;
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(serverAddress, serverTCPport);
            socket.connect(socketAddress, timeoutMS);
            if (socket.isConnected()) {
                connected = true;
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }
        return connected;
    }

    public boolean connexioLogin (){
        return false;

    **/

}

