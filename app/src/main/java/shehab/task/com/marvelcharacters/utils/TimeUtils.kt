package shehab.task.com.marvelcharacters.utils

fun getCurrentTimeStamp():String{
    val tsLong = System.currentTimeMillis() / 1000
    return  tsLong.toString()
}