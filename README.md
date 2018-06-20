# Bella-DB

![Icon](icon.png)

Fast simple json-based Database in Kotlin
<br/>
the idea is to keep a fast and simple way to store and retrieve data without using alot of resources and to maintain a flexible storage and keep it simple ! 


#### defining a database
```kotlin
    var db = Bella("test")
```
this will create a database named test to the IOAgent 
an IOAgent is a class that will differ between environments for example standard java and android io
##### don't worry about it for now!
#### getting a data set
```kotlin
    var db = Bella("test")
    var set = db.BellaSet(1)
    // or 
    var set = Bella("test").BellaSet("mySet")
    // 
```
##### note that the BellaSet(1) parameter can be either an string or integer

#### a data row
a data row is where you put and get your data
```kotlin
    // automatic id
    var row = BellaRow()
    // numeric id
    var row = BellaRow(3)
    // string id
    var row = BellaRow("awesomerow") 
```
#### putting data
```kotlin
    // same key many values !
    // inline builder
    var row = BellaRow()
        .put("hi","hi !")
        .put("hi",1)
        .put("hi",true)
        .put("hi",JSONObject())
        .put("hi",JSONArray())
    // or outside builder ;)                    
    row.put("bye","bye !") 
```
#### getting data
```kotlin
    var row = ...     
   
    var value = row.getString("hi")
    var value = row.getInt("hi")
    var value = row.getBoolean("hi")
    var value = row.getJsonObject("hi")
    var value = row.getJsonArray("hi")
    
```
#### manual access
```kotlin
    // you can set or get manually here as well !
    var row = ...     
    // json objects
    var string = row.strings
    var string = row.ints
    var string = row.booleans
    var string = row.objects
    var string = row.arrays
    
```

#### Inserting

```kotlin
    var row = BellaRow()
              .put("hi", 1)
              .put("hi", "hi")
              .put("hi", true)
              .put("hi", JSONArray().put(3))
              
    // returns the id of our row
    var id = db.insert(row)
```

#### Selecting
```kotlin
    // yes ! that simple
    // the item is now a data row
    var row = db.select(id)
    row.getInt("hi")
```

#### Editing
```kotlin
    // yes ! that simple
    // the item is now a data row
    var row = db.select(id)
    // changes the integer one in the bundle
    row.put("hi",3)
    // and simply save it
    db.save(row)
    
```

#### Deleting
```kotlin
    // select it completely 
    var row = db.select(id)
    // or 
    var row = BellaRow(3)
    
    // delete it
    db.delete(row)
    
```

#### complete example
```kotlin
    var db = Bella("test").BellaSet("my")

    var id = db.insert(BellaRow()
            .put("hi", 1)
            .put("hi", "hi")
            .put("hi", true)
            .put("hi", JSONArray().put(3)))

    var row = db.select(id)
    
    row.put("hi",2)
    db.save(row)
```

which results
<br/>
`
0.rdp
`
```json
{
   "strings":{
      "hi":"hi"
   },
   "ints":{
      "hi":2
   },
   "booleans":{
      "hi":true
   },
   "arrays":{
      "hi":[
         3
      ]
   }
}

```
