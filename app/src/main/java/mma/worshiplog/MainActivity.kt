package mma.worshiplog

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import mma.worshiplog.model.*
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with logs
    lateinit var logs: List<LogFileEntity>
    var database: AppDatabase? = null

    lateinit var emptyLogTextView: TextView
    lateinit var pickLogTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = AppDatabase.getInstance(this)

        val logFileDao: LogFileDao = database!!.logFileDao()
        val partNameDao: PartNameDao = database!!.partNameDao()
        val logDetailDao: LogDetailDao = database!!.logDetailDao()


        emptyLogTextView = findViewById(R.id.emptyLogTextView)
        pickLogTextView = findViewById(R.id.pickOldLogTextView)


        val floatingButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            val intent = Intent(this, DragAndDropActivity::class.java)
            val filename = "My message"

            intent.putExtra("fileName", filename)
            startActivity(intent)
        }


        val gettingLogs = Maybe.fromCallable { logFileDao.getAllLogs() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ logList ->
                    /* onSuccess() :) */
                    logs = logList
                    Toast.makeText(this, "Logs: " + logs.size.toString(), Toast.LENGTH_SHORT).show()

                    // Creates a vertical Layout Manager
                    loglistView.layoutManager = LinearLayoutManager(this)

                    // Access the RecyclerView Adapter and load the data into it
                    loglistView.adapter = LogAdapter(logs, this)



                    if (logs.isEmpty()) {
                        pickLogTextView.visibility = View.INVISIBLE
                    } else {
                        emptyLogTextView.visibility = View.GONE
                    }

                }) { throwable ->
                    /* onError() */
                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
                }


//        val gettingPartNames = Maybe.fromCallable { partNameDao.getAllPartNames() }
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ partNames ->
//                    /* onSuccess() :) */
//                    Toast.makeText(this, "Part names: " + partNames.size.toString(), Toast.LENGTH_SHORT).show()
//
//                }) { throwable ->
//                    /* onError() */
//                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
//                }
//
//
//        val gettingSongDetails = Maybe.fromCallable { logDetailDao.getAllLogDetails() }
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ logDetails ->
//                    /* onSuccess() :) */
//                    Toast.makeText(this, "Details: " + logDetails.size.toString(), Toast.LENGTH_SHORT).show()
//
//                }) { throwable ->
//                    /* onError() */
//                    Toast.makeText(this, "Coś poszło nie tak...", Toast.LENGTH_LONG).show()
//                }

    }

    // Adds animals to the empty animals ArrayList
//    fun prepareData() {
//        logs.add(LogFileEntity(1, "8-12-2018", "próba nabożeństwa"))
//        logs.add(LogFileEntity(1, "7-11-2018", "próba w kościele"))
//        logs.add(LogFileEntity(1, "3-12-2018", "próba"))
//        logs.add(LogFileEntity(1, "18-10-2018", "próba do nabożeństwa"))
//    }

}
