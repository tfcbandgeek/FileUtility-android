package jgappsandgames.me.fileutlitykotlin

import android.content.Context
import android.os.Environment

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.Calendar

/**
 * FileUtility
 *
 * Handles the loading and the saving of the data
 *
 * Version 1.1.0
 * - Adds External File
 * - Adds Cleaning Cache and data Directory
 */

// Data --------------------------------------------------------------------------------------------
private var data: File? = null
private var cache: File? = null

var external: File? = null
var path: String? = null

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

fun loadFilePaths(context: Context, externalPath: String): Boolean {
    path = externalPath
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
fun getFilepath(): File {
    if (data == null) throw FileUtilityException("Filepaths are not loaded")
    return data!!
}

@Throws(FileUtilityException::class)
fun getFile(filename: String? = null): File {
    if (data == null) throw FileUtilityException("Filepaths are not loaded")

    return if (filename != null) File(data!!, filename)
    else data!!
}

@Throws(FileUtilityException::class)
fun getCachePath(): File {
    if (cache == null) throw FileUtilityException("Filepaths are not loaded")
    return cache!!
}

@Throws(FileUtilityException::class)
fun getCache(filename: String? = null): File {
    if (cache == null) throw FileUtilityException("Filepaths are not loaded")

    return if (filename != null) File(cache!!, filename)
    else cache!!
}

@Throws(FileUtilityException::class)
fun getExternalPath(): File {
    if (external == null) {
        if (path != null) {
            external = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), path)
            if (!external!!.exists()) external!!.mkdirs()
        } else {
            throw FileUtilityException("The external Directory cannot be set up")
        }
    }

    return external!!
}

@Throws(FileUtilityException::class)
fun getExternal(filename: String): File {
    if (external == null) {
        if (path != null) {
            external = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), path)
            if (!external!!.exists()) external!!.mkdirs()
        } else {
            throw FileUtilityException("The external Directory cannot be set up")
        }
    }

    return File(external!!, filename)
}