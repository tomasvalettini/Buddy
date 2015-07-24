package com.durdlelabs.buddy.models.logic;

import android.content.Context;
import android.os.Environment;

import com.durdlelabs.buddy.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FilePersistance {
    String FILENAME = "contact_backup.vcf";
    Context con;
    private String appDir;
    private String appFile;

    public FilePersistance(Context con) {
        this.con = con;
        appDir = Environment.getExternalStorageDirectory().toString() + "/" + con.getResources().getString(R.string.app_name).replaceAll("\\s+", "");
        appFile = appDir + "/" + FILENAME;
    }

    public void writeToFile(String str) {
        // Toast.makeText(con, Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_LONG).show();
        File cbjsonDir = new File(appDir);
        cbjsonDir.mkdirs();
        File backup = new File(appFile);

        try {
            FileOutputStream fos = new FileOutputStream(backup);
            //fos = con.openFileOutput(appDir + "/" + FILENAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            e.printStackTrace();
        }
    }

    public String readFromToFile() {
        FileInputStream fis;
        String line = "";

        try {
            fis = con.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            line = br.readLine();

            fis.close();
            return line;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public File getAppDir() {
        return new File(appDir);
    }

    public File getAppFile() {
        File file = new File(appFile);

        if (file.exists()) {
            return new File(appFile);
        } else {
            return null;
        }
    }
}