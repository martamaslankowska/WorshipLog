package mma.worshiplog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailItemViewHolder extends RecyclerView.ViewHolder {

    public final View rootView;
    public final Button partName;
//    public final TextView extraInfo;

    DetailItemViewHolder(View view) {
        super(view);
        rootView = view;
        partName = (Button) view.findViewById(R.id.partNameButton);
//        extraInfo = (TextView) view.findViewById(R.id.extraTextView);
    }
}
