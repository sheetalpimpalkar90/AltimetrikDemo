package com.altimetrik.altimetrikdemo.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * DTO class to set/get the RSS feeds data
 */

public class RssFeedModel implements Serializable{

    @SerializedName("feed")
    FeedsModel feedsModel;

    public FeedsModel getFeedsModel() {
        return feedsModel;
    }

    public void setFeedsModel(FeedsModel feedsModel) {
        this.feedsModel = feedsModel;
    }

    /*Single object of RSS feeds*/
    public class FeedsModel implements Serializable{

        @SerializedName("results")
        ArrayList<FeedResultsModel> feedResultsModelArrayList;

        public ArrayList<FeedResultsModel> getFeedResultsModelArrayList() {
            return feedResultsModelArrayList;
        }

        public void setFeedResultsModelArrayList(ArrayList<FeedResultsModel> feedResultsModelArrayList) {
            this.feedResultsModelArrayList = feedResultsModelArrayList;
        }

        /*Class to get the feeds results array inside the feeds Object*/
        public class FeedResultsModel implements Serializable{

            @SerializedName("artistName")
            String artistName;

            @SerializedName("name")
            String songName;

            @SerializedName("artworkUrl100")
            String imageUrl;

            @SerializedName("copyright")
            String copyRight;

            @SerializedName("releaseDate")
            String songReleaseDate;

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getCopyRight() {
                return copyRight;
            }

            public void setCopyRight(String copyRight) {
                this.copyRight = copyRight;
            }

            public String getSongReleaseDate() {
                return songReleaseDate;
            }

            public void setSongReleaseDate(String songReleaseDate) {
                this.songReleaseDate = songReleaseDate;
            }

        }
    }
}
