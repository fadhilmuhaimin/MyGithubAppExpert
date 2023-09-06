package com.fadhil.mygithubapp.ui


import android.app.SearchManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fadhil.core.data.Resource
import com.fadhil.core.uiCore.SearchAdapter
import com.fadhil.mygithubapp.R
import com.fadhil.mygithubapp.Utils.SettingPreferences
import com.fadhil.mygithubapp.databinding.ActivityMainBinding
import com.fadhil.mygithubapp.ui.detail.DetailActivity
import com.fadhil.mygithubapp.ui.setting.SettingActivity
import com.fadhil.mygithubapp.ui.setting.SettingViewModel
import com.fadhil.mygithubapp.ui.setting.SettingViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel :UserViewModel by viewModels()


    private lateinit var adapterSearch : SearchAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        mainViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        adapterSearch = SearchAdapter(true)
        adapterSearch.onItemClick = {selectedData ->
            startActivity(Intent(this, DetailActivity::class.java)
                        .putExtra(DetailActivity.KEY_DETAIL,selectedData))
        }

        binding.fab.setOnClickListener {
            val uri = Uri.parse("mygithubapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        binding.rvUser.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            setHasFixedSize(true)
            adapter = adapterSearch
        }

        getQuery("fadhil")


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_appbar, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.nav_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        val txtSearch =
            searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.hint = resources.getString(R.string.search_hint)
        txtSearch.setHintTextColor(Color.LTGRAY)
        txtSearch.setTextColor(Color.WHITE)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                getQuery(query)


                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
        return super.onCreateOptionsMenu(menu)
    }

    fun getQuery(query : String){

        viewModel.getSearchResult(query).observe(this@MainActivity){ result ->
            if (result != null ){
                when(result){
                    is Resource.Success ->{
                        binding.progressBar.visibility = View.GONE
                        val userData = result.data
                        Log.d(TAG, "onCreate: isinya $userData")
                        adapterSearch.submitList(userData)

                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Error, gagal mengambil data", Toast.LENGTH_SHORT).show()

                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE


                    }

                    is Resource.Empty -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.nav_settings -> {
                startActivity(Intent(this,SettingActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}