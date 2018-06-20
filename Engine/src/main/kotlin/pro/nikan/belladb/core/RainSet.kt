package pro.nikan.belladb.core

import org.json.JSONException
import org.json.JSONObject
import pro.nikan.belladb.io.IOAgent
import java.io.File

class BellaSet {

    private var intID : Int? = null
    private var stringID : String? = null
    private var ioAgent: IOAgent

    constructor(ioAgent: IOAgent,id: String) {
        this.stringID = id
        this.ioAgent = ioAgent
        ioAgent.createFolder(id)
    }

    constructor(ioAgent: IOAgent,id: Int) {
        this.intID = id
        this.ioAgent = ioAgent
        ioAgent.createFolder(id.toString())
    }

    fun insert(BellaRow: BellaRow, overwrite:Boolean = false) : String {
        var id = BellaRow.getID()
        if(id == "default") {
            id = generateID()
        }

        var obj = JSONObject()
        obj.put("strings",BellaRow.strings)
        obj.put("ints",BellaRow.ints)
        obj.put("booleans",BellaRow.booleans)
        obj.put("objects",BellaRow.objects)
        obj.put("arrays",BellaRow.arrays)
        ioAgent.write(File(getID(),id).path,obj.toString().toByteArray(),overwrite)
        return id
    }

    fun delete(id:String) {
        ioAgent.deleteFile(File(getID(),id).path)
    }


    fun save(BellaRow: BellaRow) {
        var obj = JSONObject()
        obj.put("strings",BellaRow.strings)
        obj.put("ints",BellaRow.ints)
        obj.put("booleans",BellaRow.booleans)
        obj.put("objects",BellaRow.objects)
        obj.put("arrays",BellaRow.arrays)
        ioAgent.write(File(getID(),BellaRow.getID()).path,obj.toString().toByteArray(),true)
    }

    fun select(id: String) : BellaRow {
        val set = BellaRow(id)
        val json = JSONObject(String(ioAgent.read(File(getID(),id).path)))
        try {
            set.strings = json.getJSONObject("strings")
        } catch (e:JSONException) {

        }
        try {
            set.ints = json.getJSONObject("ints")
        } catch (e:JSONException) {

        }
        try {
            set.booleans = json.getJSONObject("booleans")
        } catch (e:JSONException) {

        }
        try {
            set.objects = json.getJSONObject("objects")
        } catch (e:JSONException) {

        }
        try {
            set.arrays = json.getJSONObject("arrays")
        } catch (e:JSONException) {

        }
        return set
    }

    fun select(id: Int) : BellaRow {
        return select(id.toString())
    }

    private var idCache = "--"

    private fun getID():String {
        if(idCache == "--") {
            idCache = when {
                stringID != null -> stringID!!
                intID != null -> intID!!.toString()
                else -> "default"
            }
        }
        return idCache
    }

    private fun generateID(increment : Int = 0) : String{
        if (ioAgent.exists(File(getID(),increment.toString()).path)) {
            return generateID(increment+1)
        } else {
            return increment.toString()
        }
    }

}