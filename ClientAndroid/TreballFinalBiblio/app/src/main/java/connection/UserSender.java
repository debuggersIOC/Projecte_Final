package connection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import model.User;

public class UserSender implements Runnable {

    User mUser;
    int opcions;


    public UserSender(User mUserm) {
        this.mUser = mUser;
        this. opcions = opcions;
    }

    @Override
    public void run() {

        ConnectionUtils.writeUser(mUser);

    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }
}


