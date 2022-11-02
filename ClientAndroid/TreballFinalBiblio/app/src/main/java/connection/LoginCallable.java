package connection;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.Callable;

import JSONUtils.JSONParser;
import model.LogInKeys;
import model.User;

public class LoginCallable implements Callable {

    public LoginCallable(LogInKeys logK) {
        mlogK = logK;
    }

    LogInKeys mlogK;

    /**
     *
     * @return returns the user information of the login. Null if the login failed.
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        ConnectionManager.connect();

        DataOutputStream out= new DataOutputStream (ConnectionManager.socket.getOutputStream());
        out.flush();
        PrintWriter writer = new PrintWriter(out, true);

        DataInputStream in = new DataInputStream (ConnectionManager.socket.getInputStream());
        Scanner scanner = new Scanner (in);


        //Enviem paraula de request

        Log.d("CONNECTIONDEBUG", "LOGIN: creat DataOutputStream");
        writer.println(ConnectionManager.KEYWORD_LOGIN);

        Log.d("CONNECTIONDEBUG", "LOGIN:print line");

        //Enviem les claus de login
        String string= JSONParser.logInKeysToJsonString(mlogK);
        writer.println(string);

        Log.d("CONNECTIONDEBUG", "LOGIN:WriteUTFKEYS" + string);

        //Rebem el token

        String token;
        if (scanner.hasNext()){
            token = scanner.nextLine();
            Log.d("CONNECTIONDEBUG", "LOGIN:token" + token);
        }
        else{
            token= null;

        }


        return token;





    }
}


