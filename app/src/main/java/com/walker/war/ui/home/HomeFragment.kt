package com.walker.war.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.walker.war.databinding.FragmentHomeBinding
import com.walker.war.di.qualifier.Test1
import com.walker.war.di.qualifier.Test2
import com.walker.war.newwork.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import okhttp3.OkHttpClient
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Test2
    @Inject
    lateinit var url: Any

    @Test2
    @Inject
    lateinit var urtl: Any

    @Inject
    lateinit var networkHelper: NetworkHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("guowtest", "home url=" + url.hashCode())

        Log.d("guowtest", "home urtl=" + urtl.hashCode())
        Log.d("guowtest", "newworkhelper=" + networkHelper.hashCode())
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}