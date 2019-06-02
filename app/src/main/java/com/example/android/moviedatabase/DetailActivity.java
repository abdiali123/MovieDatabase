package com.example.android.moviedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private TextView mMovieReleaseDate;
    private TextView mMoviePlot;
    private TextView mMovieRating;

    private static final String MOVIEDB_BASEURL =
            "https://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMoviePoster = (ImageView) findViewById(R.id.da_iv_poster);
        mMovieTitle = (TextView) findViewById(R.id.da_tv_title);
        mMovieReleaseDate = (TextView) findViewById(R.id.da_tv_release_date);
        mMoviePlot = (TextView) findViewById(R.id.da_tv_plot);
        mMovieRating = (TextView) findViewById(R.id.da_tv_rating);

        String intentTitle = getResources().getResourceName(R.string.intent_title);
        String intentPlot = getResources().getResourceName(R.string.intent_plot);
        String intentReleaseDate = getResources().getResourceName(R.string.intent_release_date);
        String intentVoting = getResources().getResourceName(R.string.intent_voting);
        String intentPoster = getResources().getResourceName(R.string.intent_poster);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(intentTitle)) {
                String mTitle = intentThatStartedThisActivity.getStringExtra(intentTitle);
                mMovieTitle.setText(mTitle);
                this.setTitle(mTitle);
            }

            if (intentThatStartedThisActivity.hasExtra(intentReleaseDate)) {
                String mReleaseDate = intentThatStartedThisActivity.getStringExtra(intentReleaseDate);
                mMovieReleaseDate.setText(mReleaseDate);
            }

            if (intentThatStartedThisActivity.hasExtra(intentPlot)) {
                String mPlot = intentThatStartedThisActivity.getStringExtra(intentPlot);
                mMoviePlot.setText(mPlot);
            }

            if (intentThatStartedThisActivity.hasExtra(intentPoster)) {
                String mUrl = intentThatStartedThisActivity.getStringExtra(intentPoster);
                String URL =  MOVIEDB_BASEURL + mUrl;
                Picasso.get().load(URL).into(mMoviePoster);
            }

            if (intentThatStartedThisActivity.hasExtra(intentVoting)) {
                String mVote = Double.toString(intentThatStartedThisActivity.getDoubleExtra(intentVoting, 0));
                CharSequence maxRating = getResources().getText(R.string.da_rating_max);
                String mRating = mVote + maxRating;
                mMovieRating.setText(mRating);
            }
        }
    }
}
