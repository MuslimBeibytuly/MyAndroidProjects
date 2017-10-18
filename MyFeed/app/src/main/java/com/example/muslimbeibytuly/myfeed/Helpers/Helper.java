package com.example.muslimbeibytuly.myfeed.Helpers;

import android.content.ContentValues;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by muslimbeibytuly on 10/9/17.
 */

public class Helper {

    public byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
