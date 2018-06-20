package pro.nikan.belladb.core

import org.json.JSONArray
import org.json.JSONObject

class BellaRow {
    private var intID : Int? = null
    private var stringID: String? = null
    constructor(id:String) {
        stringID = id
    }
    constructor(id:Int) {
        intID = id
    }
    constructor() {

    }
    var strings : JSONObject? = null
    var ints : JSONObject? = null
    var booleans : JSONObject? = null
    var objects : JSONObject? = null
    var arrays : JSONObject? = null

    fun put(key: String,value:String) : BellaRow {
        if(strings == null) strings = JSONObject()
        strings!!.put(key,value)
        return this
    }

    fun put(key: String,value:Int) : BellaRow {
        if(ints == null) ints = JSONObject()
        ints!!.put(key,value)
        return this
    }

    fun put(key: String,value:Boolean) : BellaRow {
        if(booleans == null) booleans = JSONObject()
        booleans!!.put(key,value)
        return this
    }

    fun put(key: String,value:JSONObject) : BellaRow {
        if(objects == null) objects = JSONObject()
        objects!!.put(key,value)
        return this
    }

    fun put(key: String,value:JSONArray) : BellaRow {
        if(arrays == null) arrays = JSONObject()
        arrays!!.put(key,value)
        return this
    }

    fun getString(key: String) : String? {
        if(strings != null) return strings!!.getString(key)
        return null
    }

    fun getInt(key: String) : Int? {
        if(ints != null) return ints!!.getInt(key)
        return null
    }

    fun getBoolean(key: String) : Boolean? {
        if(booleans != null) return booleans!!.getBoolean(key)
        return null
    }

    fun getJsonObject(key: String) : JSONObject? {
        if(objects != null) return objects!!.getJSONObject(key)
        return null
    }

    fun getJsonArray(key: String) : JSONArray? {
        if(arrays != null) return arrays!!.getJSONArray(key)
        return null
    }

    private var idCache = "--"

    fun getID():String {
        if(idCache == "--") {
            idCache = when {
                stringID != null -> stringID!!
                intID != null -> intID!!.toString()
                else -> "default"
            }
        }
        return idCache
    }
}