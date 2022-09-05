package fr.stephanecastrec.designpatterns.patterns.creation.singleton

object Session {
    init {

    }
    private val session = HashMap<String, String> ();
    fun setData(key: String, value: String) = session.put(key, value)
    fun getData(key: String): String? = session.get(key)
}