package com.walker.war.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.walker.war.databinding.FragmentHomeBinding
import com.walker.war.di.module.EntryPointTest
import com.walker.war.eproxy.UserControl
import com.walker.war.newwork.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var networkHelper: NetworkHelper


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
        var userControl = UserControl()
        userControl.isDebugLoggingEnabled = true
        binding.rvView.adapter = userControl.adapter
        //userControl.requestModelBuild();
        homeViewModel.list.observe(viewLifecycleOwner) {
            //(binding.rvView.adapter as MainAdapter).updateData(it as ArrayList<User>)
            userControl.setData(it)
            (binding.rvView.layoutManager as LinearLayoutManager)?.scrollToPositionWithOffset(
                lasty,
                lastx
            )
        }
        binding.sfRefresh.setOnRefreshListener {
            homeViewModel.fetchUsers {
                binding.sfRefresh.isRefreshing = false
            }
        }

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


