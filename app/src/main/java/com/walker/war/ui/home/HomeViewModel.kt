package com.walker.war.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.walker.net.HttpResult
import com.walker.war.Test
import com.walker.war.base.AppApiService
import com.walker.war.base.AppApiService.Companion.apiService
import com.walker.war.data.MainRepository
import com.walker.war.data.api.ApiService
import com.walker.war.data.model.User
import com.walker.war.di.module.TestAny
import com.walker.war.eproxy.UserPagingSource
import com.walker.war.repository.XXXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    var test111: ApiService,
    val ayn: TestAny,
    val appApiService: AppApiService
) : ViewModel() {
    var userid: Lazy<String> = lazy {
        "123"
    }
    var list = MutableLiveData<List<User>>()
    var listtest = MutableLiveData<List<User>>(null)

    var list2 = MutableLiveData<HttpResult<List<User>>>()

    var b:User = User()


    init {
        viewModelScope.launch {
            try {
                list.value = fetchUsers()!!
            } catch (e:IOException){

            }

            Timber.d(list2.value.toString())

        }
        b?.also {
            Timber.d("123="+(Any::class.java.isAssignableFrom(b::class.java)))
            Timber.d("123s="+(b is User))
        }
        b?.let {
            Timber.d("let b is null")
        }
    }

    val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }


    var flow: Flow<PagingData<User>>? = null

    var pageSource: UserPagingSource? = null

    val text: LiveData<String> = _text

    val testNoValue = MutableLiveData<String>()

    val testStateFlow = MutableStateFlow<String>("1")
    val testShareFlow = MutableSharedFlow<String>()
    val _test:StateFlow<String> =testStateFlow


    //
    suspend fun fetchUsers(): List<User> {
        //  _users.postValue(Resource.loading(null))
        var list = emptyList<User>()
        try {
            for (i in 0..10) {
                Log.d("fetuser=", "exception $i" + apiService)
            }
            var respond = appApiService.getUsers()//ayn.getUser()//mainRepository.getUsers()
            list = respond?.body()!!

        } catch (e: Exception) {
            Log.d("fetuser=", "exception =$e")
        }
        Log.d("fetuser=", "Test.test1=${Test.test1}")
        Log.d("fetuser=", "Test.test2=${Test.test1}")
//            var result = Loading("123") as RequestResult<String>
//            when (result) {
//                is Failure -> TODO()
//                is Loading -> TODO()
//                is Success -> TODO()
//            }
        return list
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