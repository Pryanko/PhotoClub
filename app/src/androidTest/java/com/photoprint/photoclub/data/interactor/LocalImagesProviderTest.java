package com.photoprint.photoclub.data.interactor;

import android.support.test.runner.AndroidJUnit4;

import com.photoprint.photoclub.data.model.ImageInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author Grigoriy Pryamov.
 */
@RunWith(AndroidJUnit4.class)
public class LocalImagesProviderTest {

    private LocalImagesProvider localImagesProvider;
    private ImageInfo imageInfo;
    private ImageInfo imageInfoOne;
    private ImageInfo imageInfoTwo;

    @Before
    public void setUp() throws Exception {
        localImagesProvider = new LocalImagesProvider(() -> null);
        imageInfo = new ImageInfo();
        imageInfoOne = new ImageInfo();
        imageInfoTwo = new ImageInfo();
        imageInfo.setName("22");
        imageInfo.setFolder("PICTURES");
        imageInfoOne.setName("IMG-20180101-WA0005.jpg");
        imageInfoOne.setFolder("WHATSAPP");
        imageInfoTwo.setName("Screenshot_20180107-133127.png");
        imageInfoTwo.setFolder("PICTURES");
    }

    @Test
    public void imageInfoTest() throws Exception {
        assertEquals(imageInfo, localImagesProvider.getImageInfo("//12/22"));
        assertEquals(imageInfoOne, localImagesProvider.getImageInfo("file:///storage/emulated/0/WhatsApp/Media/WhatsApp Images/Sent/IMG-20180101-WA0005.jpg"));
        assertEquals(imageInfoTwo, localImagesProvider.getImageInfo("file:///storage/emulated/0/Screenshot_20180107-133127.png"));
    }


}