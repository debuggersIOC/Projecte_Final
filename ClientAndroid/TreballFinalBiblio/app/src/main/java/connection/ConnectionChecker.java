package connection;

import android.content.Context;

import java.util.concurrent.Callable;

public class ConnectionChecker implements Callable {
    Context context;
    String host;
    int port;

    /**
     * Comprova la connexió
     * @param context Es el context
     * @param host host to check connection
     * @param port port of connection
     */
    public ConnectionChecker(Context context, String host, int port) {
        this.context = context;
        this. host = host;
        this.port = port;
    }
    public ConnectionChecker(Context context) {
        this.context = context;
    }


    @Override

    public String call() throws Exception {
        if (host.equals("")){//COMPROVEM AMB LES OPCIONS PER DEFECTE
            if (ConnectionUtils.itsOnline(context)) {
                return "YOU CAN CONNECT";
            }

            return "YOU CANNOT CONNECT";

        }else{//COMPROVEM AMB HOST Y PORT
            if (ConnectionUtils.itsOnline(context, host, port)) {
                return "YOU CAN CONNECT";
            }

            return "YOU CANNOT CONNECT";

        }

    }
}
