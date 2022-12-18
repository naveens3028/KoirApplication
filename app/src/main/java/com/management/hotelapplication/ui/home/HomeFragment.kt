package com.management.hotelapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.management.hotelapplication.database.AppDatabase
import com.management.hotelapplication.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by inject()
    val database : AppDatabase by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.addDatasToDb(database)

        println(database.menuDao().getData())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}