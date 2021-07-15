package com.walker.war.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walker.war.data.MainRepository
import com.walker.war.data.model.User
import com.walker.war.newwork.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    init {
        fetchUsers()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    private fun fetchUsers() {
        viewModelScope.launch {
            //  _users.postValue(Resource.loading(null))

            var list = mainRepository.getUsers()
            var a: User? = list?.body()?.get(0)
            Log.d("fetuser=", "=" + Thread.currentThread().name)
//            if (networkHelper.isNetworkConnected()) {
//                mainRepository.getUsers().let {
////                    if (it.isSuccessful) {
////                        _users.postValue(Resource.success(it.body()))
////                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
//                }
//            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}