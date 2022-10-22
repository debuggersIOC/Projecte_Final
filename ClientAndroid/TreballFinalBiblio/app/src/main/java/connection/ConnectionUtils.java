package connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import model.User;

public class ConnectionUtils {


    /**
     * Aquesta classe contè tots els metodes i atributs relacionats amb la connexió amb el servidor.
     */
    //ip i port de google per probes de connexió
    public static final String GOOGLE_IP = "8.8.8.8";
    public static final int GOOGLE_PORT = 53;

    //ip i port del servidor
    private static final String IP_TO_SERVER = "169.254.224.147";
    private static final int PORT = 8888;
    private static final int SOCKET_TIMEOUT = 5;

    static Socket socket;
    static ObjectOutputStream outputUser;

    static ObjectInputStream inputUser;


    /**
     * Comprova la connexió amb el servidor.
     *
     * @return true si s'estableix la connexió. False si falla
     */
    static public boolean checkConnexionToServer(String host, int port) {

        if (socket.isConnected()) {
            Log.d("SERVER CONNEXION STATUS", "connectat");
            return true;
        } else {
            Log.d("SERVER CONNEXION STATUS", "desconnectat");
            return false;

        }

    }

    /**
     * WRITEUSER
     * Envia la instancia d'usuari al servidor
     *
     * @param user
     */
    static public void writeUser(User user) {
        try {

            socket = new Socket();
            socket.connect(new InetSocketAddress(IP_TO_SERVER, PORT), SOCKET_TIMEOUT);
            Log.d("CONNECTIONDEBUG", "socket s'ha creat");
            if (socket == null) {
                Log.d("CONNECTIONDEBUG", "socket es null!");
            } else {

                if (socket.isConnected()) {
                    Log.d("CONNECTIONDEBUG", "connectat");

                    outputUser = new ObjectOutputStream(socket.getOutputStream());

                    outputUser.writeObject(user);
                } else {
                    Log.d("CONNECTIONDEBUG", "desconnectat");

                }
            }
        } catch (Exception e) {
            Log.d("CONNECTIONDEBUG", "NO CONNECTA");
            e.printStackTrace();

        } finally {
            try {
                Log.d("CONNECTIONDEBUG", "TANCARA SOCKET SI POT");
                if (!socket.isClosed()) {
                    socket.close();
                    Log.d("CONNECTIONDEBUG", "SOCKET TANCAT");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    /**
     * Verifies if the device is connected to the internet.
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
            sock.close();
            Log.i("CONNECTION STATUS:", "connected");

            return true;
        } catch (IOException ioException) {
            Log.i("CONNECTION STATUS:", "disconnected");
            return false;
        }
    }

    public static boolean itsOnline(Context context, String host, int port) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();

            StrictMode.setThreadPolicy(policy);

            int timeoutMs = 2000;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress(host, port);

            sock.connect(sockaddr, timeoutMs);
            sock.close();
            Log.i("CONNECTION STATUS:", "connected");

            return true;
        } catch (IOException ioException) {
            Log.i("CONNECTION STATUS:", "disconnected");
            return false;
        }
    }


/**
    //PING PING PING PING PING

    public static class Ping {
        public String net = "NO_CONNECTION";
        public String host = "";
        public String ip = "";
        public int dns = Integer.MAX_VALUE;
        public int cnt = Integer.MAX_VALUE;
    }

    public static Ping ping(URL url, Context ctx) {
        Ping r = new Ping();
        if (isNetworkConnected(ctx)) {
            r.net = getNetworkType(ctx);
            try {
                String hostAddress;
                long start = System.currentTimeMillis();
                hostAddress = InetAddress.getByName(url.getHost()).getHostAddress();
                long dnsResolved = System.currentTimeMillis();
                Socket socket = new Socket(hostAddress, url.getPort());
                socket.close();
                long probeFinish = System.currentTimeMillis();
                r.dns = (int) (dnsResolved - start);
                r.cnt = (int) (probeFinish - dnsResolved);
                r.host = url.getHost();
                r.ip = hostAddress;
            }
            catch (Exception ex) {
                Log.d("CONNECTIONDEBUG", "Ping fallit");
            }
        }
        return r;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Nullable
    public static String getNetworkType(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return activeNetwork.getTypeName();
        }
        return null;
    }

**/
    //PINK IP ADRESS
/*
    /**
     * Fa ping. 0 = tot be, 1 = sense ping, 2 = error
     * @param host
     * @param timeout
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    /**
    public static int pingHost(String host, int timeout) throws IOException,
            InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        timeout /= 1000;
        String cmd = "ping -c 1 -W " + timeout + " " + host;
        Process proc = runtime.exec(cmd);
        Log.d("CONNECTIONDEBUG", cmd);
        proc.waitFor();
        int exit = proc.exitValue();
        return exit;
    }
*/
}
