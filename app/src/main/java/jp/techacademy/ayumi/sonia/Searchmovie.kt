package jp.techacademy.ayumi.sonia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import jp.techacademy.ayumi.sonia.databinding.ActivitySearchmovieBinding

class Searchmovie : YouTubeBaseActivity() {
    // 選択肢
    private val spinnerItems = arrayOf("カテゴリー", "食べ物", "動物" , "ファッション")
    private lateinit var binding: ActivitySearchmovieBinding
    var mYouTubePlayerView: YouTubePlayerView? = null
    var mOnInitializedListener: YouTubePlayer.OnInitializedListener? = null

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
                Log.e("DEBUG", "tapped")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("DEBUG", "tapped")
            }
        }

//        mYouTubePlayerView = findViewById(R.id.Youtube_view)
//        mYouTubePlayerView.initialize(API_KEY, mOnInitializedListener);

        mOnInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                youTubePlayer: YouTubePlayer,
                b: Boolean
            ) {
                //youTubePlayer.loadVideo("BcqxLCWn-CE");
                youTubePlayer.cuePlaylist("PLWFz96RhKjnT0fw9uhZSduYcRFUqkP-Yx")
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
            }
        }

    }

    companion object {
        const val API_KEY ="AIzaSyC7tNJmirdch7FdAyT5wLWV70rhQ-gWRNI"
    }
}