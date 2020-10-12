package com.cmoney.testlab;

import com.cmoney.testlab.model.PictureDownloader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransferTest {
    @Test
    public void TestAlterUrl() {
        String originUrl = "https://via.placeholder.com/150/92c952";
        String targetUrl = "https://ipsumimage.appspot.com/150,92c952";
        assertEquals(targetUrl, PictureDownloader.getPicAlternativeUrl(originUrl));
    }
}
