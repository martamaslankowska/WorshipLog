package mma.worshiplog;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mma.worshiplog.model.AppDatabase;
import mma.worshiplog.model.LogDetail;
import mma.worshiplog.model.LogDetailDao;
import mma.worshiplog.model.LogDetailEntity;

public class DetailActivity extends AppCompatActivity {

    SectionedRecyclerViewAdapter sectionAdapter;
    AppDatabase database;
    List<LogDetail> listOfSongs;
    Filter<LogDetail, Integer> filter;
    int numberOfSongs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int logID = getIntent().getIntExtra("logID", 0);

        // Create an instance of SectionedRecyclerViewAdapter
        sectionAdapter = new SectionedRecyclerViewAdapter();

        // Create filter for list of songs filtering
        filter = new Filter<LogDetail, Integer>() {
            public boolean isMatched(LogDetail object, Integer text) {
                return object.getSongId() == text;
            }
        };

        // Init database DAO
        database = AppDatabase.getInstance(this);
        final LogDetailDao logDetailDao = database.logDetailDao();


        Disposable songsIds = Maybe.fromCallable(() -> {
            return logDetailDao.getLogDetailsSongsIds(logID);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(logSongsIds -> {

                    Disposable songs = Maybe.fromCallable(() -> {
                        return logDetailDao.getAllLogDetails(logID);
                    })
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(logDetails -> {

                                listOfSongs = logDetails;
                                numberOfSongs = logSongsIds.size();

                                for (int i=0; i<logSongsIds.size(); i++) {
                                    int songID = logSongsIds.get(i);
                                    List<LogDetail> song = new FilterList().filterList(listOfSongs, filter, songID);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        song.sort(new PartNameOrderComparator());
                                    }
                                    List<PartNameGrid> songsPartNames = new ArrayList<>();
                                    for (int j=0; j<song.size(); j++){
                                         LogDetail songPart = song.get(j);
                                        if (songPart.getPartName() != null && !songPart.getPartName().isEmpty()) {
                                            songsPartNames.add(new PartNameGrid(songPart.getPartName(), songPart.getExtraInfo()));
                                        }
                                    }
                                    String tag = String.valueOf(i+1);
                                    sectionAdapter.addSection(tag, new SongSection(this, tag + "/" + song.get(0).getSongName(), songsPartNames));
                                }

                                // Set up your RecyclerView with the SectionedRecyclerViewAdapter
                                RecyclerView recyclerView = findViewById(R.id.songListRecyclerView);

                                final int spanSize = 4;
                                GridLayoutManager glm = new GridLayoutManager(this, spanSize);
                                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        switch (sectionAdapter.getSectionItemViewType(position)) {
                                            case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                                                return spanSize;
                                            default:
                                                return 1;
                                        }
                                    }
                                });
                                recyclerView.setLayoutManager(glm);
                                recyclerView.setAdapter(sectionAdapter);


                                findViewById(R.id.addSongImageView).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        numberOfSongs++;
                                        String tag = String.valueOf(numberOfSongs);
                                        sectionAdapter.addSection(tag, new SongSection(view.getContext(), String.valueOf(numberOfSongs) + "/" + "", new ArrayList<PartNameGrid>()));
                                        int sectionPosition = sectionAdapter.getSectionPosition(tag);
                                        sectionAdapter.notifyItemInserted(sectionPosition);
                                        recyclerView.smoothScrollToPosition(sectionPosition);
                                    }
                                });

                            }
                            ,throwable -> {
                                Toast.makeText(this, "Ups, poblem...", Toast.LENGTH_LONG).show();
                            });


                }
                ,throwable -> {
                    Toast.makeText(this, "Ups, pobleeeem...", Toast.LENGTH_LONG).show();
                });



//        var database: AppDatabase? = null
//        lateinit var listOfIds: List<Int>
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_detail)
//            val idLog = intent.getIntExtra("logID", 0)
//
//            Toast.makeText(this, "ID: " + idLog.toString(), Toast.LENGTH_SHORT).show()
//
//            database = AppDatabase.getInstance(this)
//            var logDetailDao: LogDetailDao = database!!.logDetailDao()
//
//            val gettingSongDetailsIds = Maybe.fromCallable { logDetailDao.getLogDetailsSongsIds(idLog) }
//                .subscribeOn(Schedulers.computation())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ logDetailSongsIds ->
//                            /* onSuccess() :) */
//                            listOfIds = logDetailSongsIds
//
//                            Toast.makeText(this, "Lista o długości: " + listOfIds.size.toString(), Toast.LENGTH_SHORT).show()
//
//                            val songListRV = findViewById<RecyclerView>(R.id.songListView)
//                            songListRV.layoutManager = LinearLayoutManager(this)
//                            songListRV.adapter = DetailAdapter(listOfIds, this)
//
//
//                    }) { throwable ->
//                    /* onError() */
//                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
//            }




        // Create lists

        // Add your Sections



    }





//
//    private Observable<GenerosResponse> makeRequestToServiceA() {
//        return  service.getAllGeneros("movie","list","da0d692f7f62a1dc687580f79dc1e6a0"); //some network call
//    }
//
//    private Observable<ResponseMovies> makeRequestToServiceB(Genre genre) {
//        return service.getAllMovies(genre.getId(),"movies","da0d692f7f62a1dc687580f79dc1e6a0","created_at.asc"); //some network call based on response from ServiceA
//    }
//
//    void doTheJob() {
//        makeRequestToServiceA()
//                .flatMap(userResponse -> Observable.just(userResponse.getGenres()))      //get list from response
//                .flatMapIterable(baseDatas -> baseDatas)
//                .flatMap(new Func1<Genre, Observable<? extends ResponseMovies>>() {
//
//                    @Override
//                    public Observable<? extends ResponseMovies> call(Genre genre) {
//                        return makeRequestToServiceB(genre);
//                    }
//                }, new Func2<Genre, ResponseMovies, CollectionsMovies>() {
//
//                    @Override
//                    public CollectionsMovies call(Genre genre, ResponseMovies responseMovies) {
//                        return new CollectionsMovies(genre,responseMovies);
//                    }
//                }).
//                subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(....);
//    }



}



