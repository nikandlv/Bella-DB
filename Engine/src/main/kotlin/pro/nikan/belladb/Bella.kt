package pro.nikan.belladb

import android.content.Context
import pro.nikan.belladb.core.BellaSet
import pro.nikan.belladb.io.IOAgent
import pro.nikan.belladb.io.StandardJavaAgent

import java.io.File

class Bella constructor(name: String) {

    private val databaseRoot : File = File("Belladb",name)
    private var ioAgent : IOAgent
    var context:Context? = null

    constructor(context: Context,name:String) : this(name) {
        this.context = context
    }

    init {

        val isAndroid = try {
            Class.forName("android.content.Context")
            true
        } catch (e: ClassNotFoundException) {
            false
        }

        ioAgent = if(isAndroid && context != null) {
            try {
                val clazz = Class.forName("ir.Belladb.external.agents.AndroidIOAgent")
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