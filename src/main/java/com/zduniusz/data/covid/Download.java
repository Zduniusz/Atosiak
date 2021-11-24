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
    public static String downloadDataToday() throws IOException {
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

    public static String downloadDataWeek() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/items?format=json&clean=1")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful())
            throw new HttpException(response.toString());

        String responseString = Objects.requireNonNull(response.body()).string();

        response.close();

        return responseString;
    }


    public static DailyCovidStat formatDataToday(String rawData) {
        return new Gson().fromJson(rawData, DailyCovidStat.class);
    }


    public static List<DailyCovidStat> formatDataWeek(String rawData) {
        JsonArray rawArray = JsonParser.parseString(rawData).getAsJsonArray();
        JsonArray weekArray = new JsonArray();

        int j = 7;

        for (int i = 0; i < j; i++) {
            if (i != 0 && rawArray.get(rawArray.size() - 1 - i).getAsJsonObject().get("lastUpdatedAtSource").getAsString().equals(weekArray.get(weekArray.size() - 1).getAsJsonObject().get("lastUpdatedAtSource").getAsString())) {
                j += 1;
                continue;
            }

            weekArray.add(rawArray.get(rawArray.size() - 1 - i).getAsJsonObject());
        }

        return new Gson().fromJson(weekArray.toString(), new TypeToken<ArrayList<DailyCovidStat>>() {
        }.getType());
    }
}
