package com.ilyko.colorsarray

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.color_item.view.*


class ColorAdapter(private val colorItems: ArrayList<ColorItem>, private val onClickListener: OnColorClickListener) :
        RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
    private val expandedHeight: Int = 800
    private var margin: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false))
    }

    override fun getItemCount(): Int {
        return colorItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            margin = itemView.resources.getDimensionPixelSize(R.dimen.card_margin)
            tvColor.text = colorItems[position].colorTitle
            itemView.setOnClickListener { onClickListener.onItemClicked(holder.adapterPosition) }
        }
        if (colorItems[position].isChecked) {
            cardChecked(colorItems[holder.adapterPosition].color, holder);
        } else {
            cardUnchecked(colorItems[holder.adapterPosition].color, holder);
        }
    }

    private fun cardUnchecked(color: Int, holder: ViewHolder) {
        holder.tvColor.setTextColor(color)
        holder.cardView.apply {
            setCardBackgroundColor(ContextCompat.getColor(holder.cardView.context, R.color.background_grey))
            layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                setMargins(margin, margin, margin, margin)
            }
        }
    }

    private fun cardChecked(color: Int, holder: ViewHolder) {
        holder.tvColor.setTextColor(ContextCompat.getColor(holder.cardView.context, R.color.background_grey))
        holder.cardView.apply {
            setCardBackgroundColor(color)
            layoutParams = (holder.cardView.layoutParams as LayoutParams).apply {
                height = expandedHeight
                setMargins(margin, margin, margin, margin)
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvColor: TextView = view.textView
        val cardView: CardView = view.card
    }

    interface OnColorClickListener {
        fun onItemClicked(pos: Int)
    }
}
