package com.walker.war.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.walker.war.Test
import com.walker.war.data.MainRepository
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.api.ApiService
import com.walker.war.data.model.User
import com.walker.war.eproxy.UserPagingSource
import com.walker.war.newwork.NetworkHelper
import com.walker.war.newwork.RequestResult
import com.walker.war.newwork.RequestResult.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {
    var userid: Lazy<String> = lazy {
        "123"
    }

    init {
        fetchUsers()
    }

    val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    var list = MutableLiveData<List<User>>()

    var flow: Flow<PagingData<User>>? = null

    var pageSource: UserPagingSource? = null

    val text: LiveData<String> = _text

    val testNoValue = MutableLiveData<String>()

    val testStateFlow = MutableStateFlow<String>("1")
    val testShareFlow = MutableSharedFlow<String>()


    //
    public fun fetchUsers() {
        viewModelScope.launch {
            //  _users.postValue(Resource.loading(null))

            Log.d("fetuser=", "userid =" + userid.value)
            try {
                var respond = mainRepository.getUsers()
            } catch (e: Exception) {
                Log.d("fetuser=", "exception =$e")
            }
            var test1 = Test()
            var test2 = Test()
            Log.d("fetuser=", "Test.test1=${Test.test1}")
            Log.d("fetuser=", "Test.test2=${Test.test1}")
            var result = Loading("123") as RequestResult<String>
//            when (result) {
//                is Failure -> TODO()
//                is Loading -> TODO()
//                is Success -> TODO()
//            }


//            list.value = respond?.body()

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

    ///   var job: Job? = null
    var flowtest: Flow<PagingData<User>>? = null

    fun fetchUsers(action: () -> Unit): Flow<PagingData<User>> {
        action.invoke()
        return mainRepository.getData("")
//        job?.cancel()
//        job?.invokeOnCompletion {
//            Log.d("fetuser=", "cause=" + it)
//        }
        // job = viewModelScope.launch {
        //  _users.postValue(Resource.loading(null))
//            var respond = mainRepository.getUsers()
//            list.value = respond?.body()

//        flowtest = Pager(PagingConfig(pageSize = 5)) {
//            pageSource
//        }.flow.asLiveData()


//            Log.d("fetuser=", "delay start=" + Thread.currentThread().name)
//            withContext(Dispatchers.IO) {
//                delay(3000)
//            }
//            Log.d("fetuser=", "delay end=" + Thread.currentThread().name)


        // }
    }
}