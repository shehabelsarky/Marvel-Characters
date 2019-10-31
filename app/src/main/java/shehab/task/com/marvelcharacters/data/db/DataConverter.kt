package shehab.task.com.marvelcharacters.data.db

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import shehab.task.com.marvelcharacters.data.model.characters.Items


class DataConverter{
    @TypeConverter
    fun fromCountryLangList(countryLang: List<Items>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Items>>() {

        }.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<Items>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Items>>() {

        }.type
        return gson.fromJson(countryLangString, type)
    }
}