package com.zduniusz.data.covid;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetDataTest {

    @Test
    void downloadData() throws IOException {
        Download.downloadData();
    }

    @Test
    void formatData() throws IOException {
        Download.formatData(Download.downloadData());
    }
}