package com.fadhil.mygithubapp.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fadhil.core.data.Resource
import com.fadhil.mygithubapp.R
import com.fadhil.core.data.Result
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.mygithubapp.databinding.ActivityDetailBinding
import com.fadhil.mygithubapp.ui.UserViewModel
import com.fadhil.mygithubapp.ui.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var item: ItemsItem? = null


    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_DETAIL, ItemsItem::class.java)
        } else {
            intent.getParcelableExtra<ItemsItem>(KEY_DETAIL)
        }

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }






        item?.login?.let {
            viewModel.check(it).observe(this) {
                if (it != null) {
                    binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.fab.context,
                            R.drawable.favorite_fill
                        )
                    )
                    binding.fab.setOnClickListener { it1 ->
                        viewModel.delete(it.username)
                    }
                } else {
                    binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.fab.context,
                            R.drawable.favorite_outline
                        )
                    )
                    binding.fab.setOnClickListener {
                        val favorite =
                            item?.login?.let { it1 -> FavoriteUser(it1, item?.avatarUrl) }
                        favorite?.let { it1 -> viewModel.insertFavorite(it1) }
                    }
                }
            }
        }




        item?.login?.let {
            viewModel.getDetail(it).observe(this) { result ->
                if (result != null) {
                    when (result) {


                        is Resource.Empty ->{
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Error, gagal mengambil data", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val userData = result.data
                            if (userData != null) {
                                setView(userData)
                            }
                        }
                    }
                }

            }
        }


        val sectionsPagerAdapter = item?.login?.let { SectionsPagerAdapter(this, it) }
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Follower"
                }

                1 -> {
                    tab.text = "Following"
                }
            }
        }.attach()


    }

    fun setView(item: DetailResponse) {
        Glide.with(this)
            .load(item.avatarUrl)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(binding.img)



        binding.tvName.text = item.name
        binding.tvUsername.text = item.login
    }


    companion object {
        val KEY_DETAIL = "detail"
    }
}