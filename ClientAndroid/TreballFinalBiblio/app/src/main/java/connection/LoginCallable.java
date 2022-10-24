package connection;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import JSONUtils.JSONParser;
import model.LogInKeys;
import model.User;

public class LoginCallable implements Callable {

    public LoginCallable(LogInKeys logK) {
        this.logK = logK;
    }

    LogInKeys logK;


    @Override
    public Object call() throws Exception {
        ConnectionManager.connect();

        DataOutputStream out= new DataOutputStream (ConnectionManager.socket.getOutputStream());
        out.flush();
        DataInputStream in = new DataInputStream (ConnectionManager.socket.getInputStream());
        //Enviem paraula de request

        Log.d("CONNECTIONDEBUG", "LOGIN: creat DataOutputStream");
        out.writeUTF(ConnectionManager.KEYWORD_LOGIN);
        out.flush();
        Log.d("CONNECTIONDEBUG", "LOGIN:WriteUTF");

        //Enviem les claus de login
        String string= JSONParser.logInKeysToJsonString(logK);
        out.writeUTF(string);

        Log.d("CONNECTIONDEBUG", "LOGIN:WriteUTFKEYS");

        //Rebem el token

        String token = in.readUTF();

        return token;





    }
}


