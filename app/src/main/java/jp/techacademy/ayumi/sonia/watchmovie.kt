package jp.techacademy.ayumi.sonia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class watchmovie : YouTubeBaseActivity(){
    var mOnInitializedListener: YouTubePlayer.OnInitializedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchmovie)
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
        const val API_KEY = "AIzaSyC7tNJmirdch7FdAyT5wLWV70rhQ-gWRNI"
        const val YOUTUBEDATA = "AIzaSyDB5OfiCiLDPkUmhQZARe4vjgL0alFdVE8"
    }
}