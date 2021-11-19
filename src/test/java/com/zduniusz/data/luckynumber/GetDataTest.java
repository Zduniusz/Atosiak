package com.zduniusz.data.luckynumber;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class GetDataTest {

    @Test
    void formatData() throws IOException {
        System.out.println(GetData.formatData(GetData.downloadData()));
    }
}