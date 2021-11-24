package com.zduniusz.data.covid;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.api.exceptions.HttpException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Download {
    public static String downloadData() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.apify.com/v2/key-value-stores/3Po6TV7wTht4vIEid/records/LATEST?disableRedirect=true")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful())
            throw new HttpException(response.toString());

        String responseString = Objects.requireNonNull(response.body()).string();

        response.close();

        return responseString;
    }

    public static DailyCovidStat formatData(String rawData) {
        return new Gson().fromJson(rawData, DailyCovidStat.class);
    }
}
