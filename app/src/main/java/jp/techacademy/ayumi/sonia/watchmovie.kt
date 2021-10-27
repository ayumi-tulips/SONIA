package jp.techacademy.ayumi.sonia

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import jp.techacademy.ayumi.sonia.SearchMovie.Companion.API_KEY

class WatchMovie : YouTubeBaseActivity() {

    var mOnInitializedListener: YouTubePlayer.OnInitializedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_watch_movie, null) as ConstraintLayout
        setContentView(layout)

        // Activity開始時にIntentを取得し、文字列をセットする
        val intent: Intent = getIntent()
        val videId: String? = intent.getStringExtra("youtubeId")

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        mOnInitializedListener = object : YouTubePlayer.OnInitializedListener {
            // 初期化に成功した場合
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                youTubePlayer: YouTubePlayer,
                b: Boolean
            ) {
                youTubePlayer.loadVideo(videId)
            }

            // 初期化に失敗した場合
            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
            }
        }
        playerView.initialize(API_KEY, mOnInitializedListener)
    }
}