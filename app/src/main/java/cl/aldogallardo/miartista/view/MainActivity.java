package cl.aldogallardo.miartista.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.arlib.floatingsearchview.FloatingSearchView;


import cl.aldogallardo.miartista.R;
import cl.aldogallardo.miartista.adapter.SongAdapter;
import cl.aldogallardo.miartista.background.GetSongs;

import cl.aldogallardo.miartista.models.Song;
import cl.aldogallardo.miartista.models.Wrapper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingSearchView mSearchView;
    Song song = new Song();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.songsRv);


        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
               // mSearchView.swapSuggestions(newSuggestions);

                new FetchSongs().execute(oldQuery);
            }
        });



    }


    private class FetchSongs extends GetSongs{


        @Override
        protected void onPostExecute(Wrapper wrapper) {
            SongAdapter adapter = new SongAdapter();
            adapter.update(wrapper.getData());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);


        }
    }
}
