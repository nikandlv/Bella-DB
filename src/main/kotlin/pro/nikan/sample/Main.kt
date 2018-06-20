package pro.nikan.sample


import org.json.JSONArray
import pro.nikan.belladb.Bella
import pro.nikan.belladb.core.BellaRow


fun main(args : Array<String>) {

    var db = Bella("test").BellaSet(1)

    var t = db.insert(BellaRow("t")
            .put("hi", 1)
            .put("hi", "hi")
            .put("hi", true)
            .put("hi", JSONArray().put(3)))

    var row = db.select(t)

    row.put("hi",2)
    db.save(row)

    db.delete(t)

}