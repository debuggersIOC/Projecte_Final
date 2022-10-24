package connection;

import java.io.IOException;

import model.User;

public class UserSender implements Runnable {

    User mUser;

    public UserSender(User mUserm) {
        this.mUser = mUser;
    }

    @Override
    public void run() {

        ConnectionManager.connect();
        ConnectionManager.writeUserJson(mUser);
        ConnectionManager.closeSocket();

    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }
}


