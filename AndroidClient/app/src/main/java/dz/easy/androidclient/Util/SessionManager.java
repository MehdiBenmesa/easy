package dz.easy.androidclient.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by Mon pc on 12/12/2016.
 */

public class SessionManager {
    SharedPreferences pref;

    Editor editor;

    Context _context;

    int PRIVATE_MODE = Context.MODE_PRIVATE;

    private static final String PREF_NAME = "userLogin";

    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String USER_ID = "idUser";

    private static final String KEY_NAME = "name";

    private static final String KEY_EMAIL = "email";

    private static final String GOOGLE_PLUS = "googlePlus";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public Boolean getIsLogin(){
        return pref.getBoolean(IS_LOGIN,false);
    }

    public Boolean getGooglePlus(){
        return pref.getBoolean(GOOGLE_PLUS,false);
    }

    public void createLoginSession(String name, String email, int idUser){
        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_EMAIL,email);

        editor.putString(KEY_NAME,name);

        editor.putInt(USER_ID,idUser);

        editor.commit();
    }

    public void setGooglePlus(){
        editor.putBoolean(GOOGLE_PLUS,true);
        editor.commit();
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String, String>();

        user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));

        user.put(KEY_NAME,pref.getString(KEY_NAME,null));

        return user;
    }

    public void finalizeLoginSession(){
        editor.clear();
        editor.putBoolean(IS_LOGIN,false);
        editor.putBoolean(GOOGLE_PLUS,false);
        editor.commit();
    }

    public int getIdUser(){
        return pref.getInt(USER_ID,0);
    }

}
