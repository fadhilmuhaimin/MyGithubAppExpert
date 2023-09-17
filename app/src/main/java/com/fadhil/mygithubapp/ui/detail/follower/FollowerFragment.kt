package com.fadhil.mygithubapp.ui.detail.follower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fadhil.core.data.Resource
import com.fadhil.core.uiCore.SearchAdapter
import com.fadhil.mygithubapp.databinding.FragmentFollowerBinding
import com.fadhil.mygithubapp.ui.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Suppress("DEPRECATION")
class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding
    private lateinit var adapterSearch: SearchAdapter

    val viewModel: UserViewModel by viewModels()
    private var position: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments.let {
            position = it?.getInt(ARG_SECTION_NUMBER)
        }
        arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val username = arguments?.getString(ARG_SECTION_USERNAME, null)
        adapterSearch = SearchAdapter(false)
        if (position == 1) {
            username?.let {
                viewModel.getFollower(it).observe(viewLifecycleOwner) { result ->
                    when (result) {

                        is Resource.Empty -> {
                            binding?.progressBar?.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            Log.d("getDataFollower",result.message.toString())
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Error, gagal mengambil data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            Log.d("datafollower",result.data.toString())
                            binding?.progressBar?.visibility = View.GONE
                            val data = result.data
                            binding?.rvFollower?.apply {
                                layoutManager = GridLayoutManager(requireContext(), 2)
                                setHasFixedSize(true)
                                adapter = adapterSearch
                            }
                            adapterSearch.submitList(data)
                        }
                    }

                }
            }
        } else {
            username?.let {
                viewModel.getFollowing(it).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Resource.Empty -> {
                            binding?.progressBar?.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            Log.d("getDataFollower",result.message.toString())
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Error, gagal mengambil data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            Log.d("datafollowing",result.data.toString())
                            binding?.progressBar?.visibility = View.GONE
                            val data = result.data
                            binding?.rvFollower?.apply {
                                layoutManager = GridLayoutManager(requireContext(), 2)
                                setHasFixedSize(true)
                                adapter = adapterSearch
                            }
                            adapterSearch.submitList(data)
                        }
                    }

                }
            }
        }

    }


    override fun setUserVisibleHint(isVisible: Boolean) {
        super.setUserVisibleHint(isVisible)
        if (isVisible) {
//            val ftr: FragmentTransaction = fragmentManager!!.beginTransaction()
//            ftr.detach(this).attach(this).commit()
        }
    }

    fun refreshData() {
        // Call the code that fetches and updates the data here
        // For example, you can call your loadFollowersData() method
//        loadFollowersData()
    }


    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_SECTION_USERNAME = "username"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}