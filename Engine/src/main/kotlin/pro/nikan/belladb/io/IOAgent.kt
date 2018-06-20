package pro.nikan.belladb.io
import java.io.File

/**
 * implemented agents should have these operations for the target environment
 */
abstract class IOAgent(rootDirectory: String) {
    val root = File(rootDirectory)
    var extention = ".rdb"

    /**
     * writes an array of bytes into the target destination
     * @param path : file to write into
     * @param bytes : bytes to write into the file
     * @return bool representing success or failure
     */
    abstract fun write(path:String,bytes:ByteArray,overwrite:Boolean) : Boolean

    /**
     * reads back the written data or any file basically
     * @param path : file path
     * @return a byte array
     */
    abstract fun read(path:String) : ByteArray

    /**
     * creates a folder if does not exist
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun createFolder(path:String) : Boolean
    /**
     * deletes a folder if exist
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun deleteFolder(path:String) : Boolean
    /**
     * creates a file if does not exist
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun createFile(path:String) : Boolean
    /**
     * deletes a file if does not exist
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun deleteFile(path:String) : Boolean
    /**
     * checks if a file/folder exists
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun exists(path:String) : Boolean
    /**
     * checks if a file
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun isFile(path:String) : Boolean
    /**
     * checks if a folder
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun isFolder(path:String) : Boolean
    /**
     * checks if a folder
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun countFiles(path:String) : Int
    /**
     * checks if a folder
     * @param path : file path
     * @return bool representing success or failure
     */
    abstract fun size(path:String) : Long
}
