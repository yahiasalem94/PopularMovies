package com.example.android.popularmovies1.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies1.Constants;
import com.example.android.popularmovies1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    final static String PARAM_SORT = "sort";

    /* The format we want our API to return */
    private static final String format = "json";

    public static URL buildUrl(int urlNumber) {
        Uri builtUri = null;

        if ( urlNumber == 0 ) {
            builtUri = Uri.parse(Constants.POPULAR_MOVIES).buildUpon()
                    .build();
        } else if ( urlNumber == 1 ) {
            builtUri = Uri.parse(Constants.TOP_RATED_MOVIES).buildUpon()
                    .build();
        }

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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