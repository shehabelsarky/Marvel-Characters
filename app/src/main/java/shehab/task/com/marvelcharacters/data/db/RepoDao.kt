package shehab.task.com.marvelcharacters.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shehab.task.com.marvelcharacters.data.model.characters.Results

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Results):Long

    @Query("SELECT * FROM RESULTS")
    fun getAllCharacters(): LiveData<List<Results>>
}