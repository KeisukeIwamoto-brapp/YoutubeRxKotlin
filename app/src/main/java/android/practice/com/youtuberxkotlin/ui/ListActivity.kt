package android.practice.com.youtuberxkotlin.ui

import android.practice.com.youtuberxkotlin.MainApplication
import android.practice.com.youtuberxkotlin.R
import android.practice.com.youtuberxkotlin.viewmodel.ListViewModel
import android.practice.com.youtuberxkotlin.vo.Video
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var listViewModel: ListViewModel
    val adapter: ListAdapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        (getApplication() as MainApplication).getComponent().inject(this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        videoListView.adapter = adapter
        videoListView.layoutManager = layoutManager

        adapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
            override fun onClick(view: View, data: Video) {
                Toast.makeText(applicationContext, data.title, Toast.LENGTH_LONG).show()
            }
        })

        videoListView.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                loadMore()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                if (!p0.isEmpty()) {
                    search(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return true
            }
        })

    }

    fun search(query: String) {
        listViewModel.search(query)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            adapter.clear()
                            adapter.addItems(result)
                        }
                )
    }

    fun loadMore() {
        listViewModel.loadMore()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> adapter.addItems(result) }
                )
    }
}
