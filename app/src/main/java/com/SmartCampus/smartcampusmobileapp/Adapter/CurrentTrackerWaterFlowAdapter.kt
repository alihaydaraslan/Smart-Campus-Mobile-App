package com.SmartCampus.smartcampusmobileapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.SmartCampus.smartcampusmobileapp.Model.CurrentTrackerAndWaterFlow
import com.SmartCampus.smartcampusmobileapp.R


class CurrentTrackerWaterFlowAdapter(private val context: Context, private val arrayList: ArrayList<CurrentTrackerAndWaterFlow>) : BaseAdapter() {
    private lateinit var entryId: TextView
    private lateinit var current: TextView
    private lateinit var createdAt: TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView: View?
        convertView = LayoutInflater.from(context).inflate(R.layout.current_tracker_listview_item, parent, false)
        entryId = convertView.findViewById(R.id.textView1)
        current = convertView.findViewById(R.id.textView2)
        createdAt = convertView.findViewById(R.id.textView3)
        entryId.text = "Entry Id: "+arrayList[position].entryId
        current.text = "Current Data: "+arrayList[position].field1
        createdAt.text ="Created At: "+ arrayList[position].createdAt
        return convertView
    }
}