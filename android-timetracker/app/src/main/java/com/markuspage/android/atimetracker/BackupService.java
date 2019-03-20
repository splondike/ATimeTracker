package com.markuspage.android.atimetracker;

import android.app.IntentService;
import android.app.Service;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.markuspage.android.atimetracker.Activities;

/**
 * Just copies the database file out to /sdcard/.
 */
public class BackupService extends IntentService {
    public BackupService() {
        super("BackupService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(Activities.DB_FILE));
            out = new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "timetracker.db")));
            for (int c = in.read(); c != -1; c = in.read()) {
                out.write(c);
            }
            out.close();
        } catch (IOException e) {
            Log.e("BackupService", "Failed to write backup file: " + e);
        }
    }
}
