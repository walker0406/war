package com.walker.war.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.walker.war.data.MainRepository
import com.walker.war.data.model.User
import com.walker.war.newwork.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    var userid : Lazy<String> = lazy {
        "123"
    }

    init {
        fetchUsers()
    }

    val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    var list = MutableLiveData<List<User>>()

    val text: LiveData<String> = _text


    //
    public fun fetchUsers() {
        viewModelScope.launch {
            //  _users.postValue(Resource.loading(null))

            Log.d("fetuser=", "userid =" + userid.value)
            var respond = mainRepository.getUsers()
            list.value = respond?.body()

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

    var job: Job? = null
    public fun fetchUsers(action: () -> Unit) {
        action.invoke()
        job?.cancel()
        job?.invokeOnCompletion {
            Log.d("fetuser=", "cause=" + it)
        }
        job = viewModelScope.launch {
            //  _users.postValue(Resource.loading(null))
            var respond = mainRepository.getUsers()
            list.value = respond?.body()
            Log.d("fetuser=", "delay start=" + Thread.currentThread().name)
            withContext(Dispatchers.IO) {
                delay(3000)
            }
            Log.d("fetuser=", "delay end=" + Thread.currentThread().name)
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