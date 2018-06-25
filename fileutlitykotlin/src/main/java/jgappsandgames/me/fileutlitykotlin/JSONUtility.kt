package jgappsandgames.me.fileutlitykotlin

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.util.*

// Constants ---------------------------------------------------------------------------------------
private const val CALENDAR_MILIS = "milis"
private const val CALENDAR_ACTIVE = "active"

// Load Methods ------------------------------------------------------------------------------------
@Throws(FileUtilityException::class)
fun loadJSONObject(file: File): JSONObject {
    try {
        val reader = BufferedReader(FileReader(file))

        val b = StringBuilder()

        while (true) {
            val s = reader.readLine()
            if (s == null) break
            else b.append(s).append(System.lineSeparator())
        }

        return JSONObject(b.toString())
    } catch (f: FileNotFoundException) {
        f.printStackTrace()

        throw FileUtilityException("That file does not Exist")
    } catch (i: IOException) {
        i.printStackTrace()

        throw FileUtilityException("File was not loadable.")
    } catch (e: JSONException) {
        e.printStackTrace()

        throw FileUtilityException("Invalid JSONObject")
    }

}

@Throws(FileUtilityException::class)
fun loadJSONArray(file: File): JSONArray {
    try {
        val reader = BufferedReader(FileReader(file))

        val b = StringBuilder()

        while (true) {
            val s = reader.readLine()
            if (s == null) break
            else b.append(s).append(System.lineSeparator())
        }

        return JSONArray(b.toString())
    } catch (f: FileNotFoundException) {
        f.printStackTrace()

        throw FileUtilityException("That file does not Exist")
    } catch (i: IOException) {
        i.printStackTrace()

        throw FileUtilityException("File was not loadable.")
    } catch (e: JSONException) {
        e.printStackTrace()

        throw FileUtilityException("Invalid JSONArray")
    }

}

fun loadJSONCalendar(data: JSONObject): Calendar? {
    if (data.optBoolean(CALENDAR_ACTIVE, false)) {
        val c = GregorianCalendar()
        c.timeInMillis = data.optLong(CALENDAR_MILIS, 0L)
        return c
    }

    return null
}

// Save Methods ------------------------------------------------------------------------------------
@Throws(FileUtilityException::class)
fun saveJSONObject(file: File, data: JSONObject) {
    try {
        val writer = BufferedWriter(FileWriter(file))

        writer.write(data.toString(0))
        writer.flush()
        writer.close()
    } catch (e: IOException) {
        e.printStackTrace()

        throw FileUtilityException("File Saving Error")
    } catch (e: JSONException) {
        e.printStackTrace()

        throw FileUtilityException("JSON Saving Error")
    }

}

@Throws(FileUtilityException::class)
fun saveJSONArray(file: File, data: JSONArray) {
    try {
        val writer = BufferedWriter(FileWriter(file))

        writer.write(data.toString(0))
        writer.flush()
        writer.close()
    } catch (e: IOException) {
        e.printStackTrace()

        throw FileUtilityException("File Saving Error")
    } catch (e: JSONException) {
        e.printStackTrace()

        throw FileUtilityException("JSON Saving Error")
    }

}

@Throws(FileUtilityException::class)
fun saveJSONCalendar(calendar: Calendar?): JSONObject {
    val data = JSONObject()

    try {
        if (calendar == null)
            data.put(CALENDAR_ACTIVE, false)
        else {
            data.put(CALENDAR_ACTIVE, true)
            data.put(CALENDAR_MILIS, calendar.timeInMillis)
        }
    } catch (j: JSONException) {
        j.printStackTrace()

        throw FileUtilityException("JSON Exception")
    }

    return data
}