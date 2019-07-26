package com.ilyko.colorsarray

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ColorAdapter.OnColorClickListener {

    private var colorItems: ArrayList<ColorItem> = ArrayList()
    private lateinit var colorAdapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        colorItems = getColorFromXml(resources.getXml(R.xml.colors_list))
        colorAdapter = ColorAdapter(colorItems, this)
        colorRecyclerView.apply {
            adapter = colorAdapter
        }
    }

    override fun onItemClicked(pos: Int) {
        colorItems[pos].isChecked = !colorItems[pos].isChecked
        colorAdapter.notifyItemChanged(pos)
    }


}
