package com.id.parkee.commons.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.parkee.android.libraries.reactivex.DisposableViewModel

abstract class BaseViewModel : DisposableViewModel() {
    val viewState: MutableLiveData<ViewStateModel> = MutableLiveData()
    val showErrorLiveData: MutableLiveData<String> = MutableLiveData()
}
