package com.example.skincancer

import kotlinx.coroutines.flow.Flow

interface SkinCancerRepository {
    //Read
    suspend fun listSkinCancer(): List<SkinCancerEntity>

    //Create
    suspend fun createSkinCancer(skinCancer: SkinCancerEntity)

    //Update
    suspend fun updateSkinCancer(skinCancer: SkinCancerEntity)

    //Delete All SkinCancers
    suspend fun deleteSkinCancers()


    //Delete a SkinCancer by PK
	suspend fun deleteSkinCancer(id: String)
	    
    //Search with live data
    fun searchBySkinCancerid(searchQuery: String): Flow<List<SkinCancerEntity>>
    //Search with live data
    fun searchBySkinCancerdates(searchQuery: String): Flow<List<SkinCancerEntity>>
    //Search with live data
    fun searchBySkinCancerimages(searchQuery: String): Flow<List<SkinCancerEntity>>
    //Search with live data
    fun searchBySkinCanceroutcome(searchQuery: String): Flow<List<SkinCancerEntity>>

    //Search with suspend
    suspend fun searchBySkinCancerid2(searchQuery: String): List<SkinCancerEntity>
    suspend fun searchBySkinCancerdates2(searchQuery: String): List<SkinCancerEntity>
    suspend fun searchBySkinCancerimages2(searchQuery: String): List<SkinCancerEntity>
    suspend fun searchBySkinCanceroutcome2(searchQuery: String): List<SkinCancerEntity>

}
