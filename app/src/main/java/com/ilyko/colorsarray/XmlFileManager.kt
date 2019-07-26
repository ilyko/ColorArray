package com.ilyko.colorsarray

import android.graphics.Color
import org.xmlpull.v1.XmlPullParser

fun getColorFromXml(parser: XmlPullParser): ArrayList<ColorItem> {
    val colorItems: ArrayList<ColorItem> = ArrayList()

    try {
        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType == XmlPullParser.START_TAG && parser.name == "color") {
                colorItems.add(ColorItem(parser.getAttributeValue(1), Color.parseColor(parser.getAttributeValue(1))))
            }
            parser.next()
        }
    } catch (t: Throwable) {
        t.printStackTrace()
    }
    return colorItems
}