package com.example.skincancer

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.ArrayList
import android.graphics.Bitmap
import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream

class ModelFacade private constructor(context: Context) {

    private val assetManager: AssetManager = context.assets
    private var fileSystem: FileAccessor
    private var imageClassifier: ImageClassifier



    init {
    	//init
        fileSystem = FileAccessor(context)
     imageClassifier = ImageClassifier(context)
    }

    companion object {
    	private val repository by lazy { Repository() }
        private var instance: ModelFacade? = null
        fun getInstance(context: Context): ModelFacade {
            return instance ?: ModelFacade(context)
        }
    }
    

    val allSkinCancers: LiveData<List<SkinCancerEntity>> = repository.allSkinCancers.asLiveData()

    val allSkinCancerIds: LiveData<List<String>> = repository.allSkinCancerids.asLiveData()
    val allSkinCancerDatess: LiveData<List<String>> = repository.allSkinCancerdatess.asLiveData()
    val allSkinCancerImagess: LiveData<List<String>> = repository.allSkinCancerimagess.asLiveData()
    val allSkinCancerOutcomes: LiveData<List<String>> = repository.allSkinCanceroutcomes.asLiveData()
    private var currentSkinCancer: SkinCancerEntity? = null
    private var currentSkinCancers: List<SkinCancerEntity> = ArrayList()
	    
    fun searchBySkinCancerid(searchQuery: String): LiveData<List<SkinCancerEntity>>  {
        return repository.searchBySkinCancerid(searchQuery).asLiveData()
    }
    
    fun searchBySkinCancerdates(searchQuery: String): LiveData<List<SkinCancerEntity>>  {
        return repository.searchBySkinCancerdates(searchQuery).asLiveData()
    }
    
    fun searchBySkinCancerimages(searchQuery: String): LiveData<List<SkinCancerEntity>>  {
        return repository.searchBySkinCancerimages(searchQuery).asLiveData()
    }
    
    fun searchBySkinCanceroutcome(searchQuery: String): LiveData<List<SkinCancerEntity>>  {
        return repository.searchBySkinCanceroutcome(searchQuery).asLiveData()
    }
    

	fun getSkinCancerByPK(value: String): Flow<SkinCancer> {
        val res: Flow<List<SkinCancerEntity>> = repository.searchBySkinCancerid(value)
        return res.map { skinCancer ->
            val itemx = SkinCancer.createByPKSkinCancer(value)
            if (skinCancer.isNotEmpty()) {
            itemx.id = skinCancer[0].id
            }
            if (skinCancer.isNotEmpty()) {
            itemx.dates = skinCancer[0].dates
            }
            if (skinCancer.isNotEmpty()) {
            itemx.images = skinCancer[0].images
            }
            if (skinCancer.isNotEmpty()) {
            itemx.outcome = skinCancer[0].outcome
            }
            itemx
        }
    }
    
	  suspend fun createSkinCancer(x: SkinCancerEntity) {
	    repository.createSkinCancer(x)
	    currentSkinCancer = x
	}
    
    suspend fun editSkinCancer(x: SkinCancerEntity) {
		 repository.updateSkinCancer(x)
         currentSkinCancer = x
    }
		    
   fun setSelectedSkinCancer(x: SkinCancerEntity) {
		 currentSkinCancer = x
	}
		    
    suspend fun deleteSkinCancer(id: String) {
  		  repository.deleteSkinCancer(id)
  		  currentSkinCancer = null
  	}
  				
      suspend fun searchSkinCancer(dates: String) : ArrayList<SkinCancer> {
				currentSkinCancers = repository.searchBySkinCancerdates2(dates)
				var itemsList = ArrayList<SkinCancer>()
				for (x in currentSkinCancers.indices) {
					val vo: SkinCancerEntity = currentSkinCancers[x]
				    val itemx = SkinCancer.createByPKSkinCancer(vo.id)
					    itemx.id = vo.id
					    itemx.dates = vo.dates
					    itemx.images = vo.images
					    itemx.outcome = vo.outcome
				itemsList.add(itemx)
			}
		return itemsList
		}

		
    suspend fun imageRecognition(skinCancer: SkinCancer ,images: Bitmap): String {
				val result = imageClassifier.recognizeImage(images)
		        skinCancer.outcome = result[0].title  +": " + result[0].confidence
			    persistSkinCancer(skinCancer)
		    	return result[0].title  +": " + result[0].confidence
			}
				     

    suspend fun listSkinCancer(): List<SkinCancerEntity> {
	        currentSkinCancers = repository.listSkinCancer()
	        return currentSkinCancers
	    }	
	  
	suspend fun listAllSkinCancer(): ArrayList<SkinCancer> {	
		currentSkinCancers = repository.listSkinCancer()
		var res = ArrayList<SkinCancer>()
			for (x in currentSkinCancers.indices) {
					val vo: SkinCancerEntity = currentSkinCancers[x]
				    val itemx = SkinCancer.createByPKSkinCancer(vo.id)
	            itemx.id = vo.id
            itemx.dates = vo.dates
            itemx.images = vo.images
            itemx.outcome = vo.outcome
			res.add(itemx)
		}
		return res
	}

    suspend fun stringListSkinCancer(): List<String> {
        currentSkinCancers = repository.listSkinCancer()
        val res: ArrayList<String> = ArrayList()
        for (x in currentSkinCancers.indices) {
            res.add(currentSkinCancers[x].toString())
        }
        return res
    }

    suspend fun getSkinCancerByPK2(value: String): SkinCancer? {
        val res: List<SkinCancerEntity> = repository.searchBySkinCancerid2(value)
	        return if (res.isEmpty()) {
	            null
	        } else {
	            val vo: SkinCancerEntity = res[0]
	            val itemx = SkinCancer.createByPKSkinCancer(value)
	            itemx.id = vo.id
            itemx.dates = vo.dates
            itemx.images = vo.images
            itemx.outcome = vo.outcome
	            itemx
	        }
    }
    
    suspend fun retrieveSkinCancer(value: String): SkinCancer? {
            return getSkinCancerByPK2(value)
    }

    suspend fun allSkinCancerIds(): ArrayList<String> {
        currentSkinCancers = repository.listSkinCancer()
        val res: ArrayList<String> = ArrayList()
            for (skincancer in currentSkinCancers.indices) {
                res.add(currentSkinCancers[skincancer].id)
            }
        return res
    }

    fun setSelectedSkinCancer(i: Int) {
        if (i < currentSkinCancers.size) {
            currentSkinCancer = currentSkinCancers[i]
        }
    }

    fun getSelectedSkinCancer(): SkinCancerEntity? {
        return currentSkinCancer
    }

    suspend fun persistSkinCancer(x: SkinCancer) {
        val vo = SkinCancerEntity(x.id, x.dates, x.images, x.outcome)
        repository.updateSkinCancer(vo)
        currentSkinCancer = vo
    }
	

	
}
