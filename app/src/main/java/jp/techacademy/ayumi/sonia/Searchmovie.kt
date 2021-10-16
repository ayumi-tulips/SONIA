package jp.techacademy.ayumi.sonia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import jp.techacademy.ayumi.sonia.databinding.ActivitySearchmovieBinding

class Searchmovie : YouTubeBaseActivity() {
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
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("DEBUG", "tapped")
            }
        }

//        mYouTubePlayerView = findViewById(R.id.Youtube_view)
//        mYouTubePlayerView.initialize(API_KEY, mOnInitializedListener);



    }

}