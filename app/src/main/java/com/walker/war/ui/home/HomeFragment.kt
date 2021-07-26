package com.walker.war.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.paging.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.walker.war.adapter.LoadingAdapter
import com.walker.war.adapter.PageAdapter
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.model.User
import com.walker.war.databinding.FragmentHomeBinding
import com.walker.war.di.module.EntryPointTest
import com.walker.war.eproxy.PageSourceTest
import com.walker.war.eproxy.UserControl
import com.walker.war.eproxy.UserControlPage
import com.walker.war.eproxy.UserPagingSource
import com.walker.war.newwork.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import okhttp3.internal.threadName
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.resume


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var networkHelper: NetworkHelper

    @Inject
    lateinit var userSource: ApiHelper

    lateinit var adapterTest: PageAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var test: EntryPointTest = EntryPointTest()
        test.doSomething(context)
        // Log.d("guowtest", "test url=" + test.url.hashCode())

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        // binding.rvView.adapter = MainAdapter()
        var userControl = UserControlPage()
        userControl.isDebugLoggingEnabled = true
        adapterTest = PageAdapter();
        binding.rvView.adapter =
            adapterTest.withLoadStateHeader(header = LoadingAdapter())//userControl.adapter
        homeViewModel.list.observe(viewLifecycleOwner) {
            homeViewModel.viewModelScope.launch {

                (binding.rvView.layoutManager as LinearLayoutManager)?.scrollToPositionWithOffset(
                    lasty,
                    lastx
                )
            }

        }

//        userControl.addLoadStateListener {
//            it.refresh is LoadState.Loading
//            it.refresh !is LoadState.Loading
//            it.refresh is LoadState.Error
//            Log.d("guowtest", "status=" + it.toString())
//        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            try {
                homeViewModel.fetchUsers {
                    binding.sfRefresh.isRefreshing = false
                }?.let {
                    it.map { it -> it.map { it } }.collectLatest {
                        it?.apply {
                            adapterTest.submitData(this)
                        }

                    }
                }
            } catch (e: Throwable) {
                Log.d("guowtest", "throwable=" + e.toString())
            }


        }
        var job = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapterTest.loadStateFlow.collectLatest {
//                Log.d("guowtest", "refresh=" + it.refresh)
//                Log.d("guowtest", "append=" + it.append)
//                Log.d("guowtest", "preend=" + it.prepend)
            }
        }

        var scop = CoroutineScope(Dispatchers.IO + SupervisorJob())
        scop.launch {
            supervisorScope {
                launch {
                }

            }
        }


        // Scope 控制我的应用中某一层级的协程
        val scope = CoroutineScope(SupervisorJob())
        try {
            scope.launch {
                // throw IndexOutOfBoundsException()
            }

            scope.launch {
            }
        } catch (e: Throwable) {

        }


        var handler = CoroutineExceptionHandler { _, throwable ->
            {
                Log.d("guowtest", "exception")
            }
        }
        val scopetest = CoroutineScope(Job())
        var jobadf = scopetest.launch {
            Log.d("guowtest", "test1")
            try {
                throw  Throwable()
            } catch (e: Throwable) {
                Log.d("guowtest", "exception")
            }
        }
        jobadf.cancel()

        scopetest.launch {
            Log.d("guowtest", "test2")
            try {
                delay(5000)
                Log.d("guowtest", "test2")
            } catch (e: Throwable) {
                Log.d("guowtest", "exception")
            }
        }
//        scopetest.launch {
//            async {
//
//                Log.d("guowtest", "test1")
//                try {
//                    throw  Throwable()
//                } catch (e: Throwable) {
//                    Log.d("guowtest", "exception")
//                }
//            }
//
//            delay(3000)
//            launch {
//                Log.d("guowtest", "test2")
//            }


//                try {
//                    coroutineScope {
//                        try {
//                            tast2.start()
//                            delay(5000)
//                            tast1.start()
//                        } catch (e: Throwable) {
//                        }
//                    }
//                } catch (e: Throwable) {
//                }





//        adapter.addLoadStateListener {
//            Log.d("guowtest", "1refresh=" + it.refresh)
//            Log.d("guowtest", "1append=" + it.append)
//            Log.d("guowtest", "1preend=" + it.prepend)
//        }
        binding.sfRefresh.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                adapterTest.refresh()
                binding.sfRefresh.isRefreshing = false
            }

        }
        (homeViewModel.testNoValue as LiveData<*>).observe(viewLifecycleOwner) {
            Log.d("guowtest", "onchange===" + it)
        }

        var flowtest = flow {
            emit(1)
        }
        val array = arrayOf<String>("1", "2", "")
        binding.rvView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val topView: View? = binding.rvView.layoutManager?.getChildAt(0)
                lastx = topView?.getTop()!!;
                lasty = binding.rvView.layoutManager?.getPosition(topView)!!
            }

        })

        var flowtest11 = flow<Int> {
            repeat(20) {
                delay(1000)
                emit(it)
                Log.d("launchWhenStarted", "emit connect int =" + it)
            }


        }

        //挂起未取消
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            flowtest11.collect {
                Log.d("launchWhenStarted", "connect int =" + it)
            }
            repeat(20) {
                Log.d("launchWhenStarted", "int =" + it)
                delay(2000)
            }
        }
        //取消协程
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repeat(20) {
                    Log.d("repeatOnLifecycle", "int =" + it)
                    delay(2000)
                }
            }
        }
        var job123123 = viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.testStateFlow.collect {

            }
            homeViewModel.testShareFlow.collect {

            }
        }


        var testlivedata = liveData<Int> {
            Log.d("guowtest", "testlivedata thread=" + Thread.currentThread().name)
            delay(2000)
            Log.d("guowtest", "testlivedata thread=" + Thread.currentThread().name)
            emit(1)
        }
        testlivedata.observe(viewLifecycleOwner) {
            Log.d("guowtest", "testlivedata")
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            suspendCancellableCoroutine<Int> {

                it.resume(2)
                //   it.resumeWithException(Throwable("123"))

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        var lastx = 0

        @JvmStatic
        var lasty = 0
    }


}


