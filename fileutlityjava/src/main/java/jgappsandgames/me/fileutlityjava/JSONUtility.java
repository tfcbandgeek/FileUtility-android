package jgappsandgames.me.fileutlityjava;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class JSONUtility {
    // Constants -----------------------------------------------------------------------------------
    private static final String CALENDAR_DATE = "date";
    private static final String CALENDAR_ACTIVE = "active";

    // Load Methods --------------------------------------------------------------------------------
    public static JSONObject loadJSONObject(File file) throws FileUtilityException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder b = new StringBuilder();

            while (true) {
                String s = reader.readLine();
                if (s == null) break;
                else b.append(s).append(System.lineSeparator());
            }

            return new JSONObject(b.toString());
        } catch (FileNotFoundException f) {
            f.printStackTrace();

            throw new FileUtilityException("That file does not Exist");
        } catch (IOException i) {
            i.printStackTrace();

            throw new FileUtilityException("File was not loadable.");
        } catch (JSONException e) {
            e.printStackTrace();

            throw new FileUtilityException("Invalid JSONObject");
        }
    }

    public static JSONArray loadJSONArray(File file) throws FileUtilityException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder b = new StringBuilder();

            while (true) {
                String s = reader.readLine();
                if (s == null) break;
                else b.append(s).append(System.lineSeparator());
            }

            return new JSONArray(b.toString());
        } catch (FileNotFoundException f) {
            f.printStackTrace();

            throw new FileUtilityException("That file does not Exist");
        } catch (IOException i) {
            i.printStackTrace();

            throw new FileUtilityException("File was not loadable.");
        } catch (JSONException e) {
            e.printStackTrace();

            throw new FileUtilityException("Invalid JSONArray");
        }
    }

    public static Calendar loadJSONCalendar(JSONObject object) {
        if (object.optBoolean(CALENDAR_ACTIVE, false)) {
            Calendar c = new GregorianCalendar();
            c.setTimeInMillis(object.optLong(CALENDAR_DATE, 0L));
            return c;
        }

        return null;
    }

    // Save Methods --------------------------------------------------------------------------------
    public static void saveJSONObject(File file, JSONObject data) throws FileUtilityException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(data.toString(0));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

            throw new FileUtilityException("File Saving Error");
        } catch (JSONException e) {
            e.printStackTrace();

            throw new FileUtilityException("JSON Saving Error");
        }
    }

    public static void saveJSONArray(File file, JSONArray data) throws FileUtilityException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(data.toString(0));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

            throw new FileUtilityException("File Saving Error");
        } catch (JSONException e) {
            e.printStackTrace();

            throw new FileUtilityException("JSON Saving Error");
        }
    }

    public static JSONObject saveJSONCalendar(Calendar calendar) throws FileUtilityException {
        JSONObject data = new JSONObject();

        try {
            if (calendar == null) data.put(CALENDAR_ACTIVE, false);
            else {
                data.put(CALENDAR_ACTIVE, true);
                data.put(CALENDAR_DATE, calendar.getTimeInMillis());
            }
        } catch (JSONException j) {
            j.printStackTrace();

            throw new FileUtilityException("JSON Exception");
        }

        return data;
    }
}