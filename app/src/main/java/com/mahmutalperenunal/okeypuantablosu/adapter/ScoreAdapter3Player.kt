package com.mahmutalperenunal.okeypuantablosu.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mahmutalperenunal.okeypuantablosu.R
import com.mahmutalperenunal.okeypuantablosu.model.ScoreData3Player

class ScoreAdapter3Player(private val scoreList3Player: ArrayList<ScoreData3Player>) :
    RecyclerView.Adapter<ScoreAdapter3Player.ScoreViewHolder>() {

    private var clickCount = 0

    private lateinit var scoreListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        scoreListener = listener
    }


    inner class ScoreViewHolder(view: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        var score1 = view.findViewById<TextView>(R.id.thatRoundScores3Player_player1Score_text)!!
        var score2 = view.findViewById<TextView>(R.id.thatRoundScores3Player_player2Score_text)!!
        var score3 = view.findViewById<TextView>(R.id.thatRoundScores3Player_player3Score_text)!!
        var number = view.findViewById<TextView>(R.id.thatRoundScores3Player_gameNumber_text)!!

        var colorBackground =
            view.findViewById<CardView>(R.id.thatRoundScores3Player_roundColor_background)!!

        private val preferences =
            itemView.context.getSharedPreferences("clickCount3Player", Context.MODE_PRIVATE)
        private val editor = preferences.edit()

        init {

            editor.clear()
            editor.commit()
            editor.putBoolean("selected", false)
            editor.putInt("count", 0)
            editor.apply()

            itemView.setOnClickListener {
                if (clickCount >= 1) {

                    clickCount--
                    scoreList3Player[adapterPosition].isSelected = false
                    editor.putBoolean("selected", false)
                    editor.putInt("count", clickCount)
                    editor.apply()
                    colorBackground.visibility = View.VISIBLE
                    itemView.setBackgroundResource(R.drawable.shape_unselected_cardview)
                    listener.onItemClick(adapterPosition)

                } else {

                    editor.putBoolean("selected", false)
                    scoreList3Player[adapterPosition].isSelected = false
                    listener.onItemClick(adapterPosition)

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.that_round_score_3_player, parent, false)
        return ScoreViewHolder(view, scoreListener)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val newList = scoreList3Player[position]
        holder.score1.text = newList.player1_score
        holder.score2.text = newList.player2_score
        holder.score3.text = newList.player3_score
        holder.number.text = newList.gameNumber.toString()

        when (newList.color) {
            "White" -> {
                holder.colorBackground.visibility = View.GONE
            }

            "Red" -> {
                holder.colorBackground.visibility = View.VISIBLE
                holder.colorBackground.setCardBackgroundColor(Color.RED)
            }

            "Blue" -> {
                holder.colorBackground.visibility = View.VISIBLE
                holder.colorBackground.setCardBackgroundColor(Color.BLUE)
            }

            "Yellow" -> {
                holder.colorBackground.visibility = View.VISIBLE
                holder.colorBackground.setCardBackgroundColor(Color.YELLOW)
            }

            "Black" -> {
                holder.colorBackground.visibility = View.VISIBLE
                holder.colorBackground.setCardBackgroundColor(Color.BLACK)
            }

            "Fake" -> {
                holder.colorBackground.visibility = View.VISIBLE
                holder.colorBackground.setCardBackgroundColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount(): Int {
        return scoreList3Player.size
    }

}