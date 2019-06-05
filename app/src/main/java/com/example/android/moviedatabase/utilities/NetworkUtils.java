package com.example.android.moviedatabase.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String POPULAR_URL =
            "https://api.themoviedb.org/3/movie/popular";

    private static final String RATED_URL =
            "https://api.themoviedb.org/3/movie/top_rated";

    private static final String NOW_PLAYING =
            "https://api.themoviedb.org/3/movie/upcoming";

    private static String baseUrl;
    private static String language = "en-US";
    private static String api_key = "505487fe2848c4ef8cea68ddd82fe838";
    private static String page = "1";

    private final static String API_KEY_PARAM = "api_key";
    private final static String LANGUAGE_PARAM = "language";
    private final static String PAGE_PARAM = "page";

    public static URL buildUrl(int order_by_settings) {
        switch (order_by_settings) {
            case 1:
                baseUrl = RATED_URL;
                break;
            case 0:
                baseUrl = POPULAR_URL;
                break;
            case 3:
                baseUrl = NOW_PLAYING;
        }

        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, api_key)
                .appendQueryParameter(LANGUAGE_PARAM, language)
                .appendQueryParameter(PAGE_PARAM, page)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
