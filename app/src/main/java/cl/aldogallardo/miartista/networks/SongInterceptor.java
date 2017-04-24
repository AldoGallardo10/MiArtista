package cl.aldogallardo.miartista.networks;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Aldo Gallardo on 19-04-2017.
 */

public class SongInterceptor {

    private static final String BASE_URL = "https://deezerdevs-deezer.p.mashape.com/";


    public Songs get(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                    /*Common headers*/
                        .header("X-Mashape-Key","cq3LwfE723mshfPu1IqmuI3540tzp1dKzZhjsn3e7xU41ZPLXr")
                        .header("Accept", "text/plain")
                        .build();

                Response response = chain.proceed(request);
                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Songs  service = interceptor.create(Songs.class);
        return service;

    }

}
