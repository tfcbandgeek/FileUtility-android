package jgappsandgames.me.fileutlityjava;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * FileUtility
 *
 * Handles the loading and the saving of the data
 *
 * Version 1.1.0
 * - Adds External File
 * - Adds Cleaning Cache and data Directory
 */
public class FileUtility {
    // Data ----------------------------------------------------------------------------------------
    private static File data;
    private static File cache;

    public static File external;
    public static String path;

    // Static Methods ------------------------------------------------------------------------------
    public static boolean loadFilePaths(Context context) {
        data = context.getFilesDir();
        cache = context.getCacheDir();

        if (new File(data, ".firstrun").isFile()) return false;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(data, ".firstrun")));
            writer.write(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean loadFilePaths(Context context, String externalPath) {
        path = externalPath;
        data = context.getFilesDir();
        cache = context.getCacheDir();

        if (new File(data, ".firstrun").isFile()) return false;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(data, ".firstrun")));
            writer.write(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    // Internal File Methods -----------------------------------------------------------------------
    public static File getFilepath() throws FileUtilityException {
        if (data == null) throw new FileUtilityException("Filepaths are not loaded");
        return data;
    }

    public static File getFile(String filename) throws FileUtilityException {
        if (data == null) throw new FileUtilityException("Filepaths are not loaded");
        return new File(data, filename);
    }

    // Cache Methods -------------------------------------------------------------------------------
    public static File getCachePath() throws FileUtilityException {
        if (cache == null) throw new FileUtilityException("Filepaths are not loaded");
        return cache;
    }

    public static File getCache(String filename) throws FileUtilityException {
        if (cache == null) throw new FileUtilityException("Filepaths are not loaded");
        return new File(cache, filename);
    }

    // External File Methods -----------------------------------------------------------------------
    public static File getExternalPath() throws FileUtilityException {
        if (external == null) {
            if (path != null) {
                external = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), path);
                if (!external.exists()) external.mkdirs();
            } else {
                throw new FileUtilityException("The external Directory cannot be set up");
            }
        }

        return external;
    }

    public static File getExternal(String filename) throws FileUtilityException {
        if (external == null) {
            if (path != null) {
                external = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), path);
                if (!external.exists()) external.mkdirs();
            } else {
                throw new FileUtilityException("The external Directory cannot be set up");
            }
        }

        return new File(external, filename);
    }
}