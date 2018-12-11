package mma.worshiplog

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.loglist_item.view.*
import java.text.SimpleDateFormat


class LogAdapter(val items : ArrayList<Log>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

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
        var log: Log = items.get(position)
        var pattern: String = "dd-MM-yyyy"
        var dateFormat: SimpleDateFormat = SimpleDateFormat(pattern)
        holder?.dateTextView?.text = dateFormat.format(log.date)
        holder?.nameTextView?.text = log.name
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val dateTextView = view.dateTextView
    val nameTextView = view.nameTextView
}