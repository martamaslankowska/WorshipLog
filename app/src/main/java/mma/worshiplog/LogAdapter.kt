package mma.worshiplog

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.loglist_item.view.*
import java.text.SimpleDateFormat
import android.R.attr.onClick
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mma.worshiplog.model.AppDatabase
import mma.worshiplog.model.LogFileEntity


class LogAdapter(val items : List<LogFileEntity>, val context: Context)
    : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.loglist_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var log: LogFileEntity = items.get(position)
//        var pattern: String = "dd-MM-yyyy"
//        var dateFormat: SimpleDateFormat = SimpleDateFormat(pattern)

        holder?.dateTextView?.text = log.logDate
        holder?.nameTextView?.text = log.logName

        holder.itemView.setOnClickListener(View.OnClickListener {
//            Toast.makeText(context, "Blaaaaaaaah na pozycji " + position.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LogActivity::class.java)
            val logID = log.idLog
            intent.putExtra("logID", logID)
            context.startActivity(intent)
        })

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val dateTextView = view.dateTextView
    val nameTextView = view.nameTextView

}