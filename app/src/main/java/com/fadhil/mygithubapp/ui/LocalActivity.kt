package com.fadhil.mygithubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fadhil.core.uiCore.SearchAdapter
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.mygithubapp.databinding.ActivityLocalBinding

class LocalActivity : AppCompatActivity() {
    private lateinit var adapterSearch : SearchAdapter

    private lateinit var binding : ActivityLocalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalBinding.inflate(layoutInflater)

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        adapterSearch = SearchAdapter(true)

//        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
//        val viewModel: UserViewModel by viewModels {
//            factory
//        }

        binding.rvUser.apply {
            layoutManager = GridLayoutManager(this@LocalActivity,2)
            setHasFixedSize(true)
            adapter = adapterSearch
        }

//        viewModel.getfavorite().observe(this){users  ->
//            val items = arrayListOf<ItemsItem>()
//            users.map {
//                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
//                items.add(item)
//            }
//            adapterSearch.submitList(items)
//        }




        setContentView(binding.root)
    }
}