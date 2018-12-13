package mma.worshiplog;

import android.opengl.Visibility;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class SongSection extends StatelessSection {
//    List<String> itemList = Arrays.asList("Item1", "Item2", "Item3");
//
//    public MySection() {
//        // call constructor with layout resources for this Section header and items
//        super(SectionParameters.builder()
//                .itemResourceId(R.layout.section_item)
//                .headerResourceId(R.layout.section_header)
//                .build());
//    }
//
//    String title;
//    public MySection(String title, List<String> list) {
//        super(SectionParameters.builder()
//                .itemResourceId(R.layout.section_item)
//                .headerResourceId(R.layout.section_header)
//                .build());
//
//        this.title = title;
//        this.itemList = list;
//    }

    String songName;
    Integer songNumber;
    List<PartNameGrid> items;
    public SongSection(String title, List<PartNameGrid> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.grid_item)
                .headerResourceId(R.layout.grid_header)
                .build());

        String splittedSongName[] = title.split("/");

        this.items = list;
        this.songNumber = Integer.parseInt(splittedSongName[0]);
        this.songName = splittedSongName[1];
    }

    @Override
    public int getContentItemsTotal() {
        return items.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new DetailItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DetailItemViewHolder itemHolder = (DetailItemViewHolder) holder;
        // bind your view here
        PartNameGrid songPart = items.get(position);
        itemHolder.partName.setText(songPart.partName);
//        if (songPart.getExtraInfo().isEmpty() || songPart.getExtraInfo().equals(""))
//            itemHolder.extraInfo.setVisibility(View.GONE);
//        else
//            itemHolder.extraInfo.setText(songPart.extraInfo);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on " + String.valueOf(position+1) + " item",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new DetailHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        DetailHeaderViewHolder headerHolder = (DetailHeaderViewHolder) holder;

        headerHolder.headerName.setText(songName);
        headerHolder.songNumber.setText((String.valueOf(songNumber) + "."));

//        headerHolder.btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), String.format("Clicked on more button from the header of Section %s", title),
//                        Toast.LENGTH_SHORT).show();
//                Log.i("AAAAAAAAAAAAA", "OnClick made ^.^");
//            }
//        });




    }
}
