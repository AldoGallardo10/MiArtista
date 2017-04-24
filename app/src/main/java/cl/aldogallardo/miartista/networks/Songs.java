package cl.aldogallardo.miartista.networks;

import cl.aldogallardo.miartista.models.Wrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Aldo Gallardo on 19-04-2017.
 */

public interface Songs {

    @GET("search")
    Call<Wrapper> get(@Query("q") String name);
}
