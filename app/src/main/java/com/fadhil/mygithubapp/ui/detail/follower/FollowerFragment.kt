package com.fadhil.mygithubapp.ui.detail.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fadhil.core.data.Result
import com.fadhil.mygithubapp.databinding.FragmentFollowerBinding
import com.fadhil.core.uiCore.SearchAdapter
import com.fadhil.mygithubapp.ui.UserViewModel
import com.fadhil.mygithubapp.ui.ViewModelFactory


@Suppress("DEPRECATION")
class FollowerFragment : Fragment() {

    private var _binding : FragmentFollowerBinding?= null
    private val binding get() = _binding
    private lateinit var adapterSearch : SearchAdapter


    private   var position : Int? = null





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =  FragmentFollowerBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
//        val viewModel: UserViewModel by viewModels {
//            factory
//        }
        arguments.let {
             position = it?.getInt(ARG_SECTION_NUMBER)
        }
        arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val username  = arguments?.getString(ARG_SECTION_USERNAME, null)
        adapterSearch = SearchAdapter(false)
//        if (position == 1){
//            binding?.total?.text = position.toString()
//            username?.let {
//                viewModel.getFollower(it).observe(viewLifecycleOwner){ result ->
//                    when(result){
//                        is Result.Success ->{
//                            binding?.progressBar?.visibility = View.GONE
//                            val data = result.data
//                            binding?.total?.text = "Total Follower : ${data.size}"
//                            binding?.rvFollower?.apply {
//                                layoutManager = GridLayoutManager(requireContext(), 2)
//                                setHasFixedSize(true)
//                                adapter = adapterSearch
//                            }
//                            adapterSearch.submitList(data)
//                        }
//                        is Result.Error ->{
//                            binding?.progressBar?.visibility = View.GONE
//                            Toast.makeText(requireContext(), "Error, gagal mengambil data", Toast.LENGTH_SHORT).show()
//
//
//                        }
//                        is Result.Loading -> {
//                            binding?.progressBar?.visibility = View.VISIBLE
//                        }
//                    }
//
//                }
//            }
//        }else{
//            binding?.total?.text = position.toString()
//            username?.let {
//                viewModel.getFollowing(it).observe(viewLifecycleOwner){ result ->
//                    when(result){
//                        is Result.Success ->{
//                            binding?.progressBar?.visibility = View.GONE
//
//                            val data = result.data
//                            binding?.total?.text = "Total Following : ${data.size}"
//                            binding?.rvFollower?.apply {
//                                layoutManager = GridLayoutManager(requireContext(), 2)
//                                setHasFixedSize(true)
//                                adapter = adapterSearch
//                            }
//                            adapterSearch.submitList(data)
//                        }
//                        is Result.Error ->{
//                            binding?.progressBar?.visibility = View.GONE
//                            Toast.makeText(requireContext(), "Error, gagal mengambil data", Toast.LENGTH_SHORT).show()
//
//                        }
//                        is Result.Loading -> {
//                            binding?.progressBar?.visibility = View.VISIBLE
//
//                        }
//                    }
//
//                }
//            }
//        }
//
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