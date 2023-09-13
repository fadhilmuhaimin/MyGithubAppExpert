package com.fadhil.mygithubapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.domain.model.ItemsSearch
import com.fadhil.core.uiCore.SearchAdapter

import com.fadhil.mygithubapp.databinding.ActivityLocalBinding
import com.fadhil.mygithubapp.di.LocalModuleDependencies
import com.fadhil.mygithubapp.ui.UserViewModel
import com.fadhil.mygithubapp.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class LocalActivity : AppCompatActivity() {
    private lateinit var adapterSearch : SearchAdapter
    @Inject
    lateinit var factory: ViewModelFactory

    val viewModel: LocalViewModel by viewModels{
        factory
    }

    private lateinit var binding : ActivityLocalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerLocalComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    LocalModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLocalBinding.inflate(layoutInflater)

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        adapterSearch = SearchAdapter(true)
        adapterSearch.onItemClick = {selectedData ->
            startActivity(
                Intent(this, DetailActivity::class.java)
                .putExtra(DetailActivity.KEY_DETAIL,selectedData))
        }



        binding.rvUser.apply {
            layoutManager = GridLayoutManager(this@LocalActivity,2)
            setHasFixedSize(true)
            adapter = adapterSearch
        }

        viewModel.getfavorite()?.observe(this){users  ->

            adapterSearch.submitList(users)
        }

        setContentView(binding.root)
    }
}