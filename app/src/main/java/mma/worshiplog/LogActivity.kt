package mma.worshiplog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import java.util.ArrayList


class LogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val path = intent.getStringExtra("fileName")
        var messageTextView = findViewById<TextView>(R.id.textView)
    }

}