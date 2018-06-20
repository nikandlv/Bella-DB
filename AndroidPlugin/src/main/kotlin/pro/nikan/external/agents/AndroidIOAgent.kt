package pro.nikan.external.agents

import android.content.Context
import pro.nikan.belladb.io.IOAgent
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AndroidIOAgent(context : Context,root:String) : IOAgent(File(context.filesDir,root).path) {

    override fun write(path: String, bytes: ByteArray,overwrite:Boolean): Boolean {
        if(exists(path) && !overwrite) return false
        val target = File(root,path+extention)
        val fileOutputStream = FileOutputStream(target)
        fileOutputStream.write(bytes)
        fileOutputStream.close()
        return exists(path)
    }

    override fun read(path: String): ByteArray {
        val target = File(root,path+extention)
        val fileInputStream = FileInputStream(target)
        val bytes = fileInputStream.readBytes()
        fileInputStream.close()
        return bytes
    }

    override fun createFolder(path: String): Boolean {
        val file = File(root,path)
        return file.mkdirs()
    }

    override fun deleteFolder(path: String): Boolean {
        return File(root,path).delete()
    }

    override fun createFile(path: String): Boolean {
        return File(root,path+extention).createNewFile()
    }

    override fun deleteFile(path: String): Boolean {
        return File(root,path+extention).delete()
    }

    override fun exists(path: String): Boolean {
        return File(root,path+extention).exists()
    }

    override fun isFile(path: String): Boolean {
        return File(root,path+extention).isFile
    }

    override fun isFolder(path: String): Boolean {
        return File(root,path).isDirectory
    }

    override fun countFiles(path: String): Int {
        val file = File(root,path)
        if(isFolder(path)) {
            return file.list().size
        }
        return 0
    }

    override fun size(path: String): Long {
        return File(root,path+extention).length()
    }

}