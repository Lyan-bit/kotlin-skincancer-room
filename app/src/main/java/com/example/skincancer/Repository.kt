package com.example.skincancer

import kotlinx.coroutines.flow.Flow

class Repository : SkinCancerRepository  {

    private val skinCancerDao: SkinCancerEntityDao by lazy { SkincancerApplication.database.skinCancerDao() }

    val allSkinCancers: Flow<List<SkinCancerEntity>> = skinCancerDao.listSkinCancers()

    val allSkinCancerids: Flow<List<String>> = skinCancerDao.listSkinCancerids()
    val allSkinCancerdatess: Flow<List<String>> = skinCancerDao.listSkinCancerdatess()
    val allSkinCancerimagess: Flow<List<String>> = skinCancerDao.listSkinCancerimagess()
    val allSkinCanceroutcomes: Flow<List<String>> = skinCancerDao.listSkinCanceroutcomes()

    //Create
    override suspend fun createSkinCancer(skinCancer: SkinCancerEntity) {
        skinCancerDao.createSkinCancer(skinCancer)
    }

    //Read
    override suspend fun listSkinCancer(): List<SkinCancerEntity> {
        return skinCancerDao.listSkinCancer()
    }

    //Update
    override suspend fun updateSkinCancer(skinCancer: SkinCancerEntity) {
        skinCancerDao.updateSkinCancer(skinCancer)
    }

    //Delete all SkinCancers
    override suspend fun deleteSkinCancers() {
       skinCancerDao.deleteSkinCancers()
    }

    //Delete a SkinCancer
	override suspend fun deleteSkinCancer(id: String) {
	   skinCancerDao.deleteSkinCancer(id)
    }
    
     //Search with live data
     override fun searchBySkinCancerid (searchQuery: String): Flow<List<SkinCancerEntity>>  {
         return skinCancerDao.searchBySkinCancerid(searchQuery)
     }
     
     //Search with live data
     override fun searchBySkinCancerdates (searchQuery: String): Flow<List<SkinCancerEntity>>  {
         return skinCancerDao.searchBySkinCancerdates(searchQuery)
     }
     
     //Search with live data
     override fun searchBySkinCancerimages (searchQuery: String): Flow<List<SkinCancerEntity>>  {
         return skinCancerDao.searchBySkinCancerimages(searchQuery)
     }
     
     //Search with live data
     override fun searchBySkinCanceroutcome (searchQuery: String): Flow<List<SkinCancerEntity>>  {
         return skinCancerDao.searchBySkinCanceroutcome(searchQuery)
     }
     

    //Search with suspend
     override suspend fun searchBySkinCancerid2 (id: String): List<SkinCancerEntity> {
          return skinCancerDao.searchBySkinCancerid2(id)
     }
	     
    //Search with suspend
     override suspend fun searchBySkinCancerdates2 (dates: String): List<SkinCancerEntity> {
          return skinCancerDao.searchBySkinCancerdates2(dates)
     }
	     
    //Search with suspend
     override suspend fun searchBySkinCancerimages2 (images: String): List<SkinCancerEntity> {
          return skinCancerDao.searchBySkinCancerimages2(images)
     }
	     
    //Search with suspend
     override suspend fun searchBySkinCanceroutcome2 (outcome: String): List<SkinCancerEntity> {
          return skinCancerDao.searchBySkinCanceroutcome2(outcome)
     }
	     


}
