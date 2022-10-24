package connection;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.LogInKeys;
import model.User;

/**
 * Aquesta classe contè tots els metodes i atributs relacionats amb la connexió amb el servidor.
 */
public class ConnectionManager {
    final static String KEYWORD_LOGIN = "login"; // Paraula clau comunicar al servidor que es farà login.
    final static String KEYWORD_REGISTER = "register"; // Paraula clau per comunicar al servidor que es vol enregistrar un usuari.


    //ip i port de google per probes de connexió
    public static final String GOOGLE_IP = "8.8.8.8";
    public static final int GOOGLE_PORT = 53;

    //ip i port del servidor al que ens connectarem
    public static final String SERVER_IP = "10.0.2.2";
    public static final int SERVER_PORT = 21;
    private static final int SOCKET_TIMEOUT = 2000;

    public static Socket socket;

    //STREAMS
    public static ObjectOutputStream outputUser;
    public static DataOutputStream outData;

    public static ObjectInputStream inputUser;
    public static InputStreamReader inputData;

    /**
     * Connecta amb el Socket. Pren les constants SERVER_IP,  SERVER_PORT i SOCKET_TIMEOUT.
     */

    static public void connect() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();

            StrictMode.setThreadPolicy(policy);

            if (socket == null) {
                socket = new Socket();
                InetSocketAddress addr = new InetSocketAddress(SERVER_IP, SERVER_PORT);

                socket.connect(addr, SOCKET_TIMEOUT);
                Log.d("CONNECTIONDEBUG", "socket s'ha creat");

            }
            if (socket.isClosed()) {
                InetSocketAddress addr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
                socket=new Socket();
                socket.connect(addr, SOCKET_TIMEOUT);
                Log.d("CONNECTIONDEBUG", "El socket s'ha tancat just despres d'obrirlo");
            }
        } catch (IOException ioe) {
            Log.d("CONNECTIONDEBUG", ioe.getMessage());
            Log.d("CONNECTIONDEBUG", "NO CONNECTA");
            ioe.printStackTrace();

        }

    }


    /**
     * Tanca el Socket
     */
    static public void closeSocket() {
        try {
            if (!socket.isClosed()&&socket!=null) {
                socket.close();
                Log.d("CONNECTIONDEBUG", "Socket ha sigut tancat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Pren un usuari i l'envia al servidor. Cal haver obert el socket previament.
     *
     * @param user
     */
    static public void writeUser(User user) {


        //Connecta socket si no esta connectat

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();

        StrictMode.setThreadPolicy(policy);


        try {

            if (socket == null) {
                Log.d("CONNECTIONDEBUG", "socket es null!");
            }
            if (socket != null) {

                if (socket.isConnected() && !socket.isClosed()) {
                    Log.d("CONNECTIONDEBUG", "connectat");

                    //Instancia del output Stream
                    outputUser = new ObjectOutputStream(socket.getOutputStream());
                    outputUser.flush();
                    Log.d("CONNECTIONDEBUG", "creat ObjectOutputStream");
                    outData = new DataOutputStream(socket.getOutputStream());
                    outData.flush();
                    Log.d("CONNECTIONDEBUG", "creat DataOutputStream");

                    //Creem el writer que enviara la paraula.
                    //BufferedWriter writer = new BufferedWriter ( new OutputStreamWriter (outputData));

                    //Escriu paraula clau
                    //writer.write(KEYWORD_REGISTER);

                    outData.writeUTF(KEYWORD_REGISTER);
                    outData.flush();
                    Log.d("CONNECTIONDEBUG", "WriteUTF");

                    //Escriu usuari
                    Log.d("userInfo", user.getId() + user.getUsuari() + user.getPassword() + user.getType());
                    outputUser.writeObject(user);
                    outputUser.flush();

                    Log.d("CONNECTIONDEBUG", "writeObject");
                } else {
                    Log.d("CONNECTIONDEBUG", "desconnectat");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                outData.close();
                outputUser.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Pren un user i l'envia al server en format JSON. Cal haver obert el socket previament.
     *
     * @param user
     */

    static public void writeUserJson(User user) {
        //Connecta socket si no esta connectat

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();

        StrictMode.setThreadPolicy(policy);


        try {

            if (socket == null) {
                Log.d("CONNECTIONDEBUG", "socket es null!");
            }
            if (socket != null) {

                if (socket.isConnected() && !socket.isClosed()) {
                    Log.d("CONNECTIONDEBUG", "connectat");

                    //Instancia del output Stream
                    Log.d("CONNECTIONDEBUG", "creat ObjectOutputStream");
                    outData = new DataOutputStream(socket.getOutputStream());
                    outData.flush();
                    Log.d("CONNECTIONDEBUG", "creat DataOutputStream");

                    //Creem el writer que enviara la paraula.
                    //BufferedWriter writer = new BufferedWriter ( new OutputStreamWriter (outputData));

                    //Escriu paraula clau
                    //writer.write(KEYWORD_REGISTER);

                    outData.writeUTF(KEYWORD_REGISTER);
                    outData.flush();
                    Log.d("CONNECTIONDEBUG", "WriteUTF");

                    JSONObject jsonObject = new JSONObject();
                    String jsonString;
                    try {
                        jsonObject.put("id", " ASD"/*user.getId()*/);
                        jsonObject.put("usuari", "ASD" /*user.getUsuari()*/);
                        jsonObject.put("password", "ASD"/*user.getPassword()*/);
                        jsonObject.put("type", "ASD" /*user.getType()*/);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                    jsonString = jsonObject.toString();
                    outData.writeUTF(jsonString);


                    Log.d("CONNECTIONDEBUG", "writeUser");
                } else {
                    Log.d("CONNECTIONDEBUG", "desconnectat");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static public String login(LogInKeys logK) {
        String token;
        try{
            LoginCallable loginCallable = new LoginCallable (logK);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future future = executor.submit(loginCallable);
            token = (String) future.get();
            return token;


        }catch (Exception e){
            e.printStackTrace();

            return null;

        }


    }

    /**
     * Comprova que el dispositiu esta connectat a internet
     *
     * @return
     */
    public static boolean itsOnline(Context context) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();

            StrictMode.setThreadPolicy(policy);

            int timeoutMs = 2000;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress(GOOGLE_IP, GOOGLE_PORT);

            sock.connect(sockaddr, timeoutMs);
            //sock.close();
            Log.i("CONNECTION STATUS:", "connected");

            return true;
        } catch (IOException ioException) {
            Log.i("CONNECTION STATUS:", "disconnected");
            return false;
        }
    }

    /**
     * Comprova que el dispositiu esta connectat a un host especific
     *
     * @param context context de la activitat
     * @param host    host al que connectar
     * @param port    port al que connectar
     * @return
     */
    public static boolean itsOnline(Context context, String host, int port) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();

            StrictMode.setThreadPolicy(policy);

            int timeoutMs = 2000;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress(host, port);

            sock.connect(sockaddr, timeoutMs);
            //sock.close();
            Log.i("CONNECTION STATUS:", "connected");

            return true;
        } catch (IOException ioException) {
            Log.i("CONNECTION STATUS:", "disconnected");
            return false;
        }
    }


}
