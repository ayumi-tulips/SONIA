package jp.techacademy.ayumi.sonia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    var mOnInitializedListener: YouTubePlayer.OnInitializedListener? = null
    private lateinit var listener: OnClickListener
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val playerView: YouTubePlayerView
        init {
            playerView=view.findViewById(R.id.youtubeview)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerviewitem, parent, false) as ConstraintLayout

        val playerView = YouTubePlayerView(parent.context)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

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
        playerView.initialize(API_KEY, mOnInitializedListener)

        return ViewHolder(layout)

    }

    companion object {
        const val API_KEY ="AIzaSyC7tNJmirdch7FdAyT5wLWV70rhQ-gWRNI"
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
     return 8
    }
    interface OnClickListener {
        fun onItemClick(data: String)
    }
    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }
}