package mma.worshiplog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mma.worshiplog.model.AppDatabase
import mma.worshiplog.model.LogDetail
import mma.worshiplog.model.LogDetailDao


class LogActivity : AppCompatActivity() {

    var database: AppDatabase? = null
    lateinit var listOfIds: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
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

                    val songListRV = findViewById<RecyclerView>(R.id.songListRecyclerView)
                    songListRV.layoutManager = LinearLayoutManager(this)
                    songListRV.adapter = DetailAdapter(listOfIds, this)


                }) { throwable ->
                    /* onError() */
                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
                }


        val gettingSongDetails = Maybe.fromCallable { logDetailDao.getAllLogDetails(idLog) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ logDetailSongs ->
                    /* onSuccess() :) */

                    val looogs: List<LogDetail> = logDetailSongs
                    Toast.makeText(this, "Lista obiektów: " + looogs.size.toString(), Toast.LENGTH_SHORT).show()

                    Toast.makeText(this, looogs.get(0).toString(), Toast.LENGTH_SHORT).show()

                }) { throwable ->
                    /* onError() */
                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
                }
    }

}
