package mma.worshiplog

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import mma.worshiplog.model.AppDatabase
import mma.worshiplog.model.LogFileDao
import mma.worshiplog.model.PartNameDao
import mma.worshiplog.model.PartNameEntity
import java.util.*

class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with logs
    var logs: ArrayList<Log> = ArrayList()
    var database: AppDatabase? = null

    lateinit var emptyLogTextView: TextView
    lateinit var pickLogTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = AppDatabase.getInstance(this)
        var logFileDao: LogFileDao = database!!.logFileDao()

        var partNameDao: PartNameDao = database!!.partNameDao()

        emptyLogTextView = findViewById(R.id.emptyLogTextView)
        pickLogTextView = findViewById(R.id.pickOldLogTextView)


        val floatingButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            val filename = "My message"

            intent.putExtra("fileName", filename)
            startActivity(intent)
        }



        val gettingPartNames = Maybe.fromCallable { partNameDao.getAllPartNames() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ partNames ->
                    /* onSuccess() :) */
                    try {
                        Toast.makeText(this, "Było odwołanie do bazy i SUPER", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, partNames[0].namePart, Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {
                        Toast.makeText(this, "Ups, pusta baza :(", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }) { throwable ->
                    /* onError() */
                    Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show()
                }



        // Loads animals into the ArrayList
        prepareData()

        // Creates a vertical Layout Manager
        loglist.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        loglist.adapter = LogAdapter(logs, this)




        if (logs.isEmpty()) {
            pickLogTextView.visibility = View.INVISIBLE
        } else {
            emptyLogTextView.visibility = View.GONE
        }
    }

    // Adds animals to the empty animals ArrayList
    fun prepareData() {
        logs.add(Log(Date(), "aaaaaaa, cokolwiek"))
        logs.add(Log(Date(), "próba"))
        logs.add(Log(Date(2018, 12, 8), "próba nabożeństwa"))
        logs.add(Log(Date(2018, 12, 5), "próba wieczoru uwielbienia"))
    }

}
