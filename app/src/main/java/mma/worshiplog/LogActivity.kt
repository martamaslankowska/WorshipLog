package mma.worshiplog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import mma.worshiplog.model.AppDatabase
import mma.worshiplog.model.LogDetailDao
import mma.worshiplog.model.LogFileDao
import mma.worshiplog.model.PartNameDao
import java.util.ArrayList


class LogActivity : AppCompatActivity() {

    var database: AppDatabase? = null
    lateinit var listOfIds: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        val idLog = intent.getIntExtra("logID", 0)

        Toast.makeText(this, "ID: " + idLog.toString(), Toast.LENGTH_SHORT).show()

        database = AppDatabase.getInstance(this)
        var logDetailDao: LogDetailDao = database!!.logDetailDao()

        val gettingSongDetailsIds = Maybe.fromCallable { logDetailDao.getLogDetailsSongsIds(idLog) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ logDetailSongsIds ->
                    /* onSuccess() :) */
                    listOfIds = logDetailSongsIds

                    Toast.makeText(this, "Lista o długości: " + listOfIds.size.toString(), Toast.LENGTH_SHORT).show()

                    val songListRV = findViewById<RecyclerView>(R.id.songListView)
                    songListRV.layoutManager = LinearLayoutManager(this)
                    songListRV.adapter = DetailAdapter(listOfIds, this)


                }) { throwable ->
                    /* onError() */
                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
                }


//        val gettingSongDetails = Maybe.fromCallable { logDetailDao.getLogDetails(idLog) }
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ logDetailSongs ->
//                    /* onSuccess() :) */
//                    Toast.makeText(this, "Lista obiektów: " + logDetailSongs.size.toString(), Toast.LENGTH_SHORT).show()
//
//                }) { throwable ->
//                    /* onError() */
//                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
//                }
    }

}
