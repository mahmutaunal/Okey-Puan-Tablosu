package com.mahmutalperenunal.okeypuantablosu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahmutalperenunal.okeypuantablosu.R
import com.mahmutalperenunal.okeypuantablosu.model.SkorData3Kisi

class SkorAdapter3Kisi (private val skorList3Kisi: ArrayList<SkorData3Kisi>) : RecyclerView.Adapter<SkorAdapter3Kisi.SkorViewHolder>() {

    private var clickCount = 0


    /**
     * item click
     */
    private lateinit var skorListener: OnItemClickListener

    interface OnItemClickListener { fun onItemClick(position: Int) }

    fun setOnItemClickListener(listener: OnItemClickListener) { skorListener = listener }

    /**
     * longItemClick
     */
    private lateinit var skorListenerLong: OnItemLongClickListener

    interface OnItemLongClickListener { fun onItemLongClick(position: Int) }

    fun setOnItemLongClickListener(listenerLong: OnItemLongClickListener) { skorListenerLong = listenerLong }


    inner class SkorViewHolder(view: View, listener: OnItemClickListener, listenerLong: OnItemLongClickListener) : RecyclerView.ViewHolder(view) {
        var skor1 = view.findViewById<TextView>(R.id.skor1_text)!!
        var skor2 = view.findViewById<TextView>(R.id.skor2_text)!!
        var skor3 = view.findViewById<TextView>(R.id.skor3_text)!!
        var number = view.findViewById<TextView>(R.id.gameNumber_3Kisi_text)!!

        val selectIcon: ImageView = itemView.findViewById(R.id.skor3_selectIcon)

        private val preferences = itemView.context.getSharedPreferences("clickCount3Kisi", Context.MODE_PRIVATE)
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
                    skorList3Kisi[adapterPosition].isSelected = false
                    editor.putBoolean("selected", false)
                    editor.putInt("count", clickCount)
                    editor.apply()
                    selectIcon.visibility = View.GONE
                    itemView.setBackgroundResource(R.drawable.shape_unselected_cardview)
                    listener.onItemClick(adapterPosition)

                } else {

                    editor.putBoolean("selected", false)
                    skorList3Kisi[adapterPosition].isSelected = false
                    listener.onItemClick(adapterPosition)

                }
            }

            itemView.setOnLongClickListener {

                if (clickCount >= 1) {
                    clickCount--
                    skorList3Kisi[adapterPosition].isSelected = true
                    editor.putBoolean("selected", false)
                    editor.putInt("count", clickCount)
                    editor.apply()
                    selectIcon.visibility = View.GONE
                    itemView.setBackgroundResource(R.drawable.shape_unselected_cardview)
                    listenerLong.onItemLongClick(adapterPosition)
                    return@setOnLongClickListener true
                } else {
                    clickCount++
                    skorList3Kisi[adapterPosition].isSelected = true
                    editor.putBoolean("selected", true)
                    editor.putInt("count", clickCount)
                    editor.apply()
                    selectIcon.visibility = View.VISIBLE
                    itemView.setBackgroundResource(R.drawable.shape_selected_cardview)
                    listenerLong.onItemLongClick(adapterPosition)
                    return@setOnLongClickListener true
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_skor_3_kisi, parent, false)
        return SkorViewHolder(view, skorListener, skorListenerLong)
    }

    override fun onBindViewHolder(holder: SkorViewHolder, position: Int) {
        val newList = skorList3Kisi[position]
        holder.skor1.text = newList.oyuncu1_skor
        holder.skor2.text = newList.oyuncu2_skor
        holder.skor3.text = newList.oyuncu3_skor
        holder.number.text = newList.gameNumber.toString()
    }

    override fun getItemCount(): Int {
        return skorList3Kisi.size
    }

}