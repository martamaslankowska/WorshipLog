package mma.worshiplog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailHeaderViewHolder extends RecyclerView.ViewHolder {

    public final EditText headerName;
    public final TextView songNumber;

    DetailHeaderViewHolder(View view) {
        super(view);
        headerName = (EditText) view.findViewById(R.id.songNameEditText);
        songNumber = (TextView) view.findViewById(R.id.songNumberTextView);
    }

}
