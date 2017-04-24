package cl.aldogallardo.miartista.background;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import cl.aldogallardo.miartista.models.Wrapper;
import cl.aldogallardo.miartista.networks.SongInterceptor;
import cl.aldogallardo.miartista.networks.Songs;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Aldo Gallardo on 20-04-2017.
 */

public class GetSongs extends AsyncTask<String, Void, Wrapper> {



    @Override
    protected Wrapper doInBackground(String... params) {
        Context context;
        Songs request = new SongInterceptor().get();
        Call<Wrapper> call = request.get(params[0]);

        try {
            Response<Wrapper> response = call.execute();
            Log.d("CODE", String.valueOf(response.code()));
            if (200 == response.code() && response.isSuccessful()) {
                Log.d("SONG", String.valueOf(response.body()));
               return response.body();
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
