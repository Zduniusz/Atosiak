package com.zduniusz.data.covid;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class GetDataTest {

    @Test
    void downloadData() throws IOException {
        Download.downloadDataToday();
    }

    @Test
    void formatData() throws IOException {
        Download.formatDataToday(Download.downloadDataToday());
    }
}