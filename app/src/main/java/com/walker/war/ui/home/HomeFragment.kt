package com.walker.war.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.walker.war.SelectLoopUp
import com.walker.war.adapter.PageAdapter
import com.walker.war.databinding.FragmentHomeBinding
import com.walker.war.di.module.EntryPointTest
import com.walker.war.eproxy.UserControlPage
import com.walker.war.newwork.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
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

//    @Inject
//    lateinit var userSource: ApiHelper

    lateinit var adapterTest: PageAdapter

    private var tracker: SelectionTracker<Long>? = null


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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel

        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        // binding.rvView.adapter = MainAdapter()
        var userControl = UserControlPage()


        userControl.isDebugLoggingEnabled = true
        adapterTest = PageAdapter()
        adapterTest.setHasStableIds(true)
        binding.rvView.adapter = adapterTest
        //adapterTest.withLoadStateHeader(header = LoadingAdapter())//userControl.adapter
        homeViewModel.list.observe(viewLifecycleOwner) {
            homeViewModel.viewModelScope.launch {
                adapterTest.submitList(it)

//                (binding.rvView.layoutManager as LinearLayoutManager)?.scrollToPositionWithOffset(
//                    lasty,
//                    lastx
//                )
            }

        }
//        tracker = SelectionTracker.Builder<Long>(
//            "selection-1",
//            binding.rvView,
//            StableIdKeyProvider(binding.rvView),
//            SelectLoopUp(binding.rvView),
//            StorageStrategy.createLongStorage()
//        ).withSelectionPredicate(
//            SelectionPredicates.createSelectAnything()
//        ).build()
//        adapterTest.setTracker(tracker)
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

        Timber.d("Activity Created 222");
        viewLifecycleOwner.lifecycleScope.launch {
            flow<String> {
                try {
                    repeat(10) {
                        Log.d("guowtestflow", "emit= $it")
                        emit(it.toString())
                    }
                } catch (e: Exception) {
                    Log.d("guowtestflow", "cancle")
                }
            }.buffer()
                .take(2)//.distinctUntilChanged { old, new -> return@distinctUntilChanged old == new }
                .collect {
                    Log.d("guowtestflow", "emit collectLatest= $it")
                    delay(5000)
                }
        }
        Log.d("guowtestflow", "end")


//        userControl.addLoadStateListener {
//            it.refresh is LoadState.Loading
//            it.refresh !is LoadState.Loading
//            it.refresh is LoadState.Error
//            Log.d("guowtest", "status=" + it.toString())
//        }


//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            try {
//                homeViewModel.fetchUsers {
//                    binding.sfRefresh.isRefreshing = false
//                }?.let {
//                    it.map { it -> it.map { it } }.collectLatest {
//                        it?.apply {
//                         //   adapterTest.submitData(this)
//                        }
//
//                    }
//                }
//            } catch (e: Throwable) {
//                Log.d("guowtest", "throwable=" + e.toString())
//            }
//
//
//        }

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
            run {
                Log.d("guowtestexception", "test1 exception")
            }
        }
        val scopeTest = CoroutineScope(SupervisorJob())
//        try {
//            scopeTest.launch(handler) {
//                launch() {
//                    try {
//                        delay(Long.MAX_VALUE)
//                    } finally {
//                        Log.d("guowtestexception", "exception21112")
//                    }
//
//                }
//                launch(Job()) {
//                    throw  Throwable()
//                }
//
//            }
//        } catch (e: Throwable) {
//            Log.d("guowtestexception", "scopeTest=$e")
//
//        }
        //  scopeTest.launch {
        var jobtest = Job()
        scopeTest.launch(jobtest) {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                Log.d("guowtestexception", "exception21112")
            }

        }
        scopeTest.launch(jobtest) {
            // throw  Throwable()
            Log.d("guowtestexception", "test cancle")
        }
        // }


        binding.sfRefresh.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                //  adapterTest.refresh()
                binding.sfRefresh.isRefreshing = false
            }

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
        var cannle = Channel<String>() {

        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeat(2) {
                Log.d("testflow3", "channel produce ")
                cannle.send("2")
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeat(3) {
                Log.d("testflow3", "channel produce")
                cannle.send("3")
            }

        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeat(5) {
                Log.d("testflow3", "channel receive =" + cannle.receive())
            }
            cannle.receive()
            Log.d("testflow3", "channel receive over")

        }


        var receiveChannel = viewLifecycleOwner.lifecycleScope.produce<Int> {
            repeat(10) {
                send(it)
                Log.d("testflow", "channel produce $it")
            }
            Log.d("testflow", "channel produce over")

        }


//        viewLifecycleOwner.lifecycleScope.launch() {
//            flow {
//                emit(1)
//            }.flowOn(Dispatchers.Main).map { it ->
//                "String"
//            }.flowOn(Dispatchers.IO).collect {
//                withContext(Dispatchers.IO) {
//                    repeat(10){
//                        cannle.send("test"+"$it")
//                    }
//                    cannle.close()
//                }
//
//
//            }
//        }
        viewLifecycleOwner.lifecycleScope.launch {
//            for(y in cannle) {
//                Log.d("testflow", "channel name =" +y)
//            }
            receiveChannel.consumeEach {
                delay(1000)
                Log.d("testflow", "channel consumeEach=" + it)
            }
            Log.d("testflow", "channel over")
//            repeat(10){
//                Log.d("testflow", "channel name =" + cannle.receive())
//            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
//            for(y in cannle) {
//                Log.d("testflow", "channel name =" +y)
//            }
            receiveChannel.consumeEach {
                delay(2000)
                Log.d("testflow2", "channel consumeEach=" + it)
            }
            Log.d("testflow", "channel over")
//            repeat(10){
//                Log.d("testflow", "channel name =" + cannle.receive())
//            }
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

        val inttest by lazy {
            2
        }

    }


}


