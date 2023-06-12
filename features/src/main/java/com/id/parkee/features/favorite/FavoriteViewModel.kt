package com.id.parkee.features.favorite

import androidx.lifecycle.MutableLiveData
import com.id.parkee.commons.ui.viewmodel.BaseViewModel
import com.id.parkee.room.data.FavoriteMovieData
import com.id.parkee.room.domain.RoomRepositoryContract

class FavoriteViewModel(
    private val roomRepositoryContract: RoomRepositoryContract
) : BaseViewModel() {
    val getFavoriteMovieListLiveData: MutableLiveData<List<FavoriteMovieData>> = MutableLiveData()

    fun getFavoriteMovieList() {
        getFavoriteMovieListLiveData.value = roomRepositoryContract.getFavoriteMovieList()
    }
}
