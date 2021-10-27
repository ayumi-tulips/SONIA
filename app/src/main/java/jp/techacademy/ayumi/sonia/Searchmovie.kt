package jp.techacademy.ayumi.sonia

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.gson.Gson
import jp.techacademy.ayumi.sonia.databinding.ActivitySearchMovieBinding
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class SearchMovie : YouTubeBaseActivity() {
    private lateinit var binding: ActivitySearchMovieBinding
    lateinit var mAdapter: RecyclerViewAdapter
    //    var videoIdList = mutableListOf<String>("qU8vMeK4Sj8")
    lateinit var videoIdList : MutableList<String>
    //    var videoTitleList = mutableListOf<String>("テレビ東京スポーツ")
    lateinit var videoTitleList : MutableList<String>

    // 選択肢
    private val spinnerItems = arrayOf(
        "カテゴリー",
        "ユースロールモデル",
        "IT・テクノロジー",
        "イノベーター" ,
        "社会貢献・地域活性化",
        "研究・開発",
        "メディカル・ヘルスケア",
        "経営・マネジメント",
        "人を支える",
        "国際"
    )
    private var searchCategory = "健康"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(applicationContext, R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // spinnerにadapterをセット
        binding.spinner.adapter = adapter

//        videoIdList = getVideoIdsData(searchCategory)
        videoIdList = mutableListOf<String>("qU8vMeK4Sj8")
//        videoTitleList = getVideoTitleData(searchCategory)
        videoTitleList = mutableListOf<String>("テレビ東京スポーツ")

        val recyclerView = findViewById<RecyclerView>(jp.techacademy.ayumi.sonia.R.id.recyclerView)

        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(this)

        // CustomAdapterの生成と設定
        mAdapter = RecyclerViewAdapter(videoIdList, videoTitleList)
        mAdapter.setOnClickListener(
            object :  RecyclerViewAdapter.OnClickListener {
                override fun onItemClick(data: String) {
                    val intent = Intent(applicationContext, WatchMovie::class.java)
                    intent.putExtra("youtubeId", data)
                    startActivity(intent)
                }
            }
        )
        recyclerView.adapter = mAdapter

        // リスナー登録
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // スピナーでアイテムが選択された時に呼ばれる。
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                searchCategory = spinnerParent.selectedItem as String
                videoTitleList = mutableListOf<String>(searchCategory)
//                videoIdList = getVideoIdsData(searchCategory)
//                videoTitleList = getVideoTitleData(searchCategory)
                Log.e("DEBUG", "videoIdList: $videoIdList")
                Log.e("DEBUG", "videoTitleList: $videoTitleList")
                mAdapter = RecyclerViewAdapter(videoIdList, videoTitleList)
                mAdapter.notifyDataSetChanged()
                recyclerView.adapter = mAdapter
            }

            // スピナーでアイテムが選択されなかった時に呼ばれる。
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    // 検索結果の取得
    private fun getVideoIdsData(category: String): MutableList<String> {
        val url = StringBuilder()
            .append(SEARCH_URL)
            .append("?key=").append(YOUTUBE_DATA_V3_API_KEY)
            .append("&part=").append("snippet")
            .append("&type=").append("video")
            .append("&q=").append(category)
            .toString()
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) { // Error時の処理
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) { // 成功時の処理
                var list: List<ResponseData.Items>? = null
                response.body?.string()?.also {
                    val apiResponse = Gson().fromJson(it, ResponseData::class.java)
                    Log.e("DEBUG", "apiResponse : $apiResponse")
                    list = apiResponse.items
                    if (list != null) {
                        for (i in list!!.indices) {
                            var videoId: String? = null
                            videoId = list!!.get(i).id?.videoId
                            videoIdList.add(videoId!!)
                        }
                        for (i in list!!.indices) {
                            var videoTitle: String? = null
                            videoTitle = list!!.get(i).snippet?.title
                            videoTitleList.add(videoTitle!!)
                        }
                    }
                }
            }
        })
        return videoIdList
    }

    private fun getVideoTitleData(category: String): MutableList<String> {
        val url = StringBuilder()
            .append(SEARCH_URL)
            .append("?key=").append(YOUTUBE_DATA_V3_API_KEY)
            .append("&part=").append("snippet")
            .append("&type=").append("video")
            .append("&q=").append(category)
            .toString()
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) { // Error時の処理
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) { // 成功時の処理
                var list: List<ResponseData.Items>? = null
                response.body?.string()?.also {
                    val apiResponse = Gson().fromJson(it, ResponseData::class.java)
                    Log.e("DEBUG", "apiResponse : $apiResponse")
                    list = apiResponse.items
                    if (list != null) {
                        for (i in list!!.indices) {
                            var videoTitle: String? = null
                            videoTitle = list!!.get(i).snippet?.title
                            videoTitleList.add(videoTitle!!)
                        }
                    }
                }
            }
        })
        return videoTitleList
    }

    companion object {
        //        const val API_KEY = "AIzaSyBYh6pkUTNJTNEoIirKnaLK_if4F3jzqiY"
//        const val YOUTUBE_DATA_V3_API_KEY = "AIzaSyDeY8wjUsB_znx26SizxM2-zyMV9Vl78tQ"
        const val SEARCH_URL = "https://www.googleapis.com/youtube/v3/search"
    }
}