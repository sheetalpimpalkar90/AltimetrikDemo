package com.altimetrik.altimetrikdemo.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.altimetrik.altimetrikdemo.R;
import com.altimetrik.altimetrikdemo.helpers.SPDSingleton;
import com.altimetrik.altimetrikdemo.models.RssFeedModel;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

/**
 * Adapter to show the RSS feeds items
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.ViewHolder> {

    private ArrayList<RssFeedModel.FeedsModel.FeedResultsModel> resultsModelArrayList;
    Activity activityParent;
    boolean switchStatus;
    Transformation mTransformation;

    public FeedsAdapter(Activity activityParent, ArrayList<RssFeedModel.FeedsModel.FeedResultsModel> resultsModelArrayList, boolean switchStatus) {
        this.activityParent = activityParent;
        this.resultsModelArrayList = resultsModelArrayList;
        this.switchStatus = switchStatus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rss_feed_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(switchStatus){
            mTransformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp((float) (holder.imgPicture.getLayoutParams().height / 2.0))
                    .borderColor(Color.WHITE)
                    .borderWidthDp(2)
                    .oval(true)
                    .build();

            Picasso.with(activityParent)
                    .load(resultsModelArrayList.get(position).getImageUrl())
                    .fit()
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(mTransformation)
                    .into(holder.imgPicture);
        }else{
            holder.imgPicture.setVisibility(View.GONE);
        }


        holder.txtSongName.setText(resultsModelArrayList.get(position).getSongName());
        holder.txtArtistName.setText(resultsModelArrayList.get(position).getArtistName());
        holder.txtCopyRight.setText(resultsModelArrayList.get(position).getCopyRight());
//        holder.txtDate.setText(SPDSingleton.getInstance().getDate(resultsModelArrayList.get(position).getSongReleaseDate()));
    }

    @Override
    public int getItemCount() {
        return resultsModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgPicture;
        public TextView txtArtistName, txtSongName, txtCopyRight, txtDate;

        public ViewHolder(View v) {
            super(v);
            imgPicture = (ImageView) v.findViewById(R.id.picture_ImageViewID);
            txtArtistName = (TextView) v.findViewById(R.id.artistname_TextViewID);
            txtSongName = (TextView) v.findViewById(R.id.songName_TextViewID);
            txtCopyRight = (TextView) v.findViewById(R.id.copyRight_TextViewID);
            txtDate = (TextView) v.findViewById(R.id.date_textViewID);
        }
    }
}

