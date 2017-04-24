package cl.aldogallardo.miartista.background;

import android.app.Application;
import android.content.Context;

/**
 * Created by Aldo Gallardo on 24-04-2017.
 */

public class MyAplication  extends Application{

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyAplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyAplication.context;
    }
}
