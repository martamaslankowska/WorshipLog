package mma.worshiplog

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.loglist_item.view.*
import kotlinx.android.synthetic.main.songlist_item.view.*
import mma.worshiplog.model.LogFileEntity


class DetailAdapter(val items : List<Int>, val context: Context)
    : RecyclerView.Adapter<ViewHolderDetail>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDetail {
        return ViewHolderDetail(LayoutInflater.from(context).inflate(R.layout.songlist_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolderDetail, position: Int) {
        var songId: Int = items.get(position)
        holder?.songIdTextView.text = songId.toString() + "."
    }
}

class ViewHolderDetail (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val songIdTextView = view.songNumberTextView

}