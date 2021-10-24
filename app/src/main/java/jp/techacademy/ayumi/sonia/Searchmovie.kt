package jp.techacademy.ayumi.sonia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.gson.Gson
import jp.techacademy.ayumi.sonia.databinding.ActivitySearchmovieBinding
import jp.techacademy.ayumi.sonia.watchmovie.Companion.YOUTUBEDATA
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class Searchmovie : YouTubeBaseActivity() {
    var videoList = mutableListOf<String>("qU8vMeK4Sj8")
    lateinit var mAdapter: RecyclerViewAdapter
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
        "国際")
    private var searchCategory = "カテゴリー"
    private lateinit var binding: ActivitySearchmovieBinding
    var mYouTubePlayerView: YouTubePlayerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchmovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ArrayAdapter
        val adapter = ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // spinnerにadapterをセット
        binding.spinner.adapter = adapter

        // リスナー登録
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                searchCategory = spinnerParent.selectedItem as String
                Log.e("DEBUG", "spinner is tapped.")
                Log.e("DEBUG", "tapped")
                videoList = updateData(searchCategory)
                mAdapter = RecyclerViewAdapter(videoList)
                val recyclerView = findViewById<RecyclerView>(jp.techacademy.ayumi.sonia.R.id.recyclerView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("DEBUG", "tapped")
            }
        }

        val recyclerView = findViewById<RecyclerView>(jp.techacademy.ayumi.sonia.R.id.recyclerView)

// LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(this)

// CustomAdapterの生成と設定
        mAdapter = RecyclerViewAdapter(videoList)
        mAdapter.setOnClickListener(
            object :  RecyclerViewAdapter.OnClickListener {
                override fun onItemClick(data: String) {
                    // データ（Youtube動画）を渡して、画面遷移する。
                    val context = applicationContext
                    val intent = Intent(context, watchmovie::class.java)
                    intent.putExtra("youtubeId", data)
                    context.startActivity(intent)
                }
            }
        )
        recyclerView.adapter = mAdapter
//        mYouTubePlayerView = findViewById(R.id.Youtube_view)
//        mYouTubePlayerView.initialize(API_KEY, mOnInitializedListener);



    }
    // 検索結果の取得
    private fun updateData(category: String): MutableList<String> {
        val url = StringBuilder()
            .append("https://www.googleapis.com/youtube/v3/search")
            .append("?key=").append(YOUTUBEDATA) // Apiを使うためのApiKey

            .append("&part=").append("snippet")
            .append("&type=").append("video")
            .append("&q=").append(category) // 検索カテゴリー
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
                            var text: String? = null
                            text = list!!.get(i).id?.videoId
                            videoList.add(text!!)
                        }
                    }
                }
            }
        })
        return videoList
    }
}