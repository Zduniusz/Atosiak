package com.zduniusz.data.monitors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.api.exceptions.HttpException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetMonitor {
    public static String downloadData() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://raw.githubusercontent.com/Zduniusz/Atosiak/master/src/main/resources/timedependentdata/Monitor.json")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful())
            throw new HttpException(response.toString());

        return Objects.requireNonNull(response.body()).string();
    }

    public static List<Monitor> formatData(String rawData) {
        return new Gson().fromJson(rawData, new TypeToken<ArrayList<Monitor>>() {
        }.getType());
    }

    public List<Monitor> getMonitorsFromResources() {
        Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                .getResourceAsStream("/timedependentdata/Monitor.json")));
        return new Gson().fromJson(reader, new TypeToken<ArrayList<Monitor>>() {
        }.getType());
    }
}
