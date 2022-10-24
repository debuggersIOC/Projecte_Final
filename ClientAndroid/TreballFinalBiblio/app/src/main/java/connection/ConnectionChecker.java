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
        host ="";
    }


    @Override

    public Boolean call() throws Exception {
        if (host.equals("")){//COMPROVEM Si els camps son buits per colorcar LES OPCIONS PER DEFECTE
            if (ConnectionManager.itsOnline(context)) {
                return true;
            }

            return false;

        }else{//COMPROVEM AMB HOST Y PORT
            if (ConnectionManager.itsOnline(context, host, port)) {
                return true;
            }

            return false;

        }

    }
}
