package com.example.skincancer

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SkinCancerEntityDao {
    //LiveData
    //Read (list entity)
    @Query("SELECT * FROM skinCancerTable")
    fun listSkinCancers(): Flow<List<SkinCancerEntity>>

    //Read (list id)
	@Query("SELECT id FROM skinCancerTable")
	fun listSkinCancerids (): Flow<List<String>>
    //Read (list dates)
	@Query("SELECT dates FROM skinCancerTable")
	fun listSkinCancerdatess (): Flow<List<String>>
    //Read (list images)
	@Query("SELECT images FROM skinCancerTable")
	fun listSkinCancerimagess (): Flow<List<String>>
    //Read (list outcome)
	@Query("SELECT outcome FROM skinCancerTable")
	fun listSkinCanceroutcomes (): Flow<List<String>>

	//Suspend
    //Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createSkinCancer (skinCancer: SkinCancerEntity)

    //Read (list entity with suspend)
    @Query("SELECT * FROM skinCancerTable")
    suspend fun listSkinCancer(): List<SkinCancerEntity>

    //Update
    @Update
    suspend fun updateSkinCancer (skinCancer: SkinCancerEntity)

    //Delete all records
    @Query("DELETE FROM skinCancerTable")
    suspend fun deleteSkinCancers ()

    //Delete a single record by PK
    @Query("DELETE FROM skinCancerTable WHERE id = :id")
    suspend fun deleteSkinCancer (id: String)
    
    //Search with live data
	@Query("SELECT * FROM  skinCancerTable WHERE id LIKE :searchQuery ")
	fun searchBySkinCancerid(searchQuery: String): Flow<List<SkinCancerEntity>>
	@Query("SELECT * FROM  skinCancerTable WHERE dates LIKE :searchQuery ")
	fun searchBySkinCancerdates(searchQuery: String): Flow<List<SkinCancerEntity>>
	@Query("SELECT * FROM  skinCancerTable WHERE images LIKE :searchQuery ")
	fun searchBySkinCancerimages(searchQuery: String): Flow<List<SkinCancerEntity>>
	@Query("SELECT * FROM  skinCancerTable WHERE outcome LIKE :searchQuery ")
	fun searchBySkinCanceroutcome(searchQuery: String): Flow<List<SkinCancerEntity>>

    //Search with suspend
    @Query("SELECT * FROM  skinCancerTable WHERE id LIKE :searchQuery")
	suspend fun searchBySkinCancerid2(searchQuery: String): List<SkinCancerEntity>
    @Query("SELECT * FROM  skinCancerTable WHERE dates LIKE :searchQuery")
	suspend fun searchBySkinCancerdates2(searchQuery: String): List<SkinCancerEntity>
    @Query("SELECT * FROM  skinCancerTable WHERE images LIKE :searchQuery")
	suspend fun searchBySkinCancerimages2(searchQuery: String): List<SkinCancerEntity>
    @Query("SELECT * FROM  skinCancerTable WHERE outcome LIKE :searchQuery")
	suspend fun searchBySkinCanceroutcome2(searchQuery: String): List<SkinCancerEntity>

}
