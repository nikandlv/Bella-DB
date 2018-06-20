package pro.nikan.belladb

import android.content.Context
import pro.nikan.belladb.core.BellaSet
import pro.nikan.belladb.io.IOAgent
import pro.nikan.belladb.io.StandardJavaAgent

import java.io.File

class Bella  {
    var name : String
    constructor(name: String) {
        this.name = name
        databaseRoot = File("Belladb",name)
        construct()
    }
    private var databaseRoot : File
    private lateinit var ioAgent : IOAgent
    private lateinit var context:Context

    constructor(context: Context,name:String){
        this.context = context
        this.name = name
        databaseRoot = File("Belladb",name)
        construct()
    }

    fun construct() {

        val isAndroid = try {
            Class.forName("android.content.Context")
            true
        } catch (e: ClassNotFoundException) {
            false
        }

        ioAgent = if(isAndroid) {
            try {
                val clazz = Class.forName("pro.nikan.external.agents.AndroidIOAgent")
                if(clazz.superclass == IOAgent::class.java) {
                    val construct= clazz.getConstructor(Context::class.java,String::class.java)
                    construct.newInstance(context,databaseRoot.absolutePath) as IOAgent
                } else {
                    throw Exception("AndroidIOAgent is not valid")
                }
            } catch (e:ClassNotFoundException) {
                throw Exception("AndroidIOAgent not found do you have the dependency ?")
            }
        } else {
            StandardJavaAgent(databaseRoot.path)
        }
        if (!databaseRoot.exists()) {
            ioAgent.createFolder("/")
        }
    }

    fun BellaSet(id:String): BellaSet {
        return BellaSet(ioAgent,id)
    }

    fun BellaSet(id:Int):BellaSet {
        return BellaSet(ioAgent,id)
    }

    fun setIOAgent(ioAgent: IOAgent) {
        this.ioAgent = ioAgent
    }

}