package JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import model.LogInKeys;
import model.User;

public class JSONParser {

    public static LogInKeys jsonStringToLoginKeys (String string){
        //String --> Json --> LogInKeys
        try{
            JSONObject json = new JSONObject(string);
            LogInKeys loginKeys = new LogInKeys(json.getString("userName"), json.getString("password"));
            return loginKeys;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static String logInKeysToJsonString (LogInKeys logK){
        //LogInKeys --> Json --> String
        //*LogInKeys --> Json
        JSONObject jsonObject = new JSONObject();
        String jsonString;
        try {
            jsonObject.put("userName", logK.getUserName() );
            jsonObject.put("password", logK.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();

        }
        //*Json --> String
        jsonString = jsonObject.toString();
        return jsonString;
    }

    public static String userToJsonString(User user){
        //User --> JSONObject --> String
        //Tornem l'user en un objecte JSON
        JSONObject jsonUser = new JSONObject();
        try{
            jsonUser.put("id", user.getId());
            jsonUser.put("usuari", user.getUsuari());
            jsonUser.put("password", user.getPassword());
            jsonUser.put("type", user.getType());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //Tornem l'user a un String
        String stringUser = jsonUser.toString();

        return stringUser;

    }
}
