package jgappsandgames.me.fileutlitykotlin

import android.content.Context
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.Calendar

// Data --------------------------------------------------------------------------------------------
private var data: File? = null
private var cache: File? = null

// Methods -----------------------------------------------------------------------------------------
fun loadFilePaths(context: Context): Boolean {
    data = context.filesDir
    cache = context.cacheDir

    if (File(data, ".firstrun").isFile) return false

    try {
        val writer = BufferedWriter(FileWriter(File(data, ".firstrun")))
        writer.write(Calendar.getInstance().timeInMillis.toString())
        writer.flush()
        writer.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return true
}

@Throws(FileUtilityException::class)
fun getFile(filename: String? = null): File {
    if (data == null) throw FileUtilityException("Filepaths are not loaded")

    return if (filename != null) File(data!!, filename)
    else data!!
}

@Throws(FileUtilityException::class)
fun getCache(filename: String? = null): File {
    if (cache == null) throw FileUtilityException("Filepaths are not loaded")

    return if (filename != null) File(cache!!, filename)
    else cache!!
}