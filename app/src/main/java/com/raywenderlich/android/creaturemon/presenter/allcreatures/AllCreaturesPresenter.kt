package com.raywenderlich.android.creaturemon.presenter.allcreatures

import androidx.lifecycle.LiveData
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.RoomRepository
import com.raywenderlich.android.creaturemon.presenter.BasePresenter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AllCreaturesPresenter(
        private val repository: CreatureRepository = RoomRepository()
): BasePresenter<AllCreaturesContract.View>(), AllCreaturesContract.Presenter {

    override fun getAllCreatures(): LiveData<List<Creature>> {
        return runBlocking {
            repository.getAllCreatures()
        }
    }

    override fun clearAllCreatures() {
        presenterScope.launch {
            repository.clearAllCreatures()
        }
        getView()?.showCreaturesCleared()
    }
}