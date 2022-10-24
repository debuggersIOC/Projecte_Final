package ramos.ioc.treballfinalbiblio;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import connection.ConnectionManager;
import model.User;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ramos.ioc.treballfinalbiblio", appContext.getPackageName());
    }

    @Test
    public void send () {
        User user = new User("prova", "asd", "asd", "0");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("request", "register");
            jsonObject.put("id",user.getId());
            jsonObject.put("usuari",user.getUsuari());
            jsonObject.put("password",user.getPassword());
            jsonObject.put("type",user.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String json = jsonObject.toString();

        Log.d("TEST" , json);
    }
}