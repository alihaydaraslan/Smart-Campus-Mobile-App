package com.SmartCampus.smartcampusmobileapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.SmartCampus.smartcampusmobileapp.Model.CurrentTrackerAndWaterFlow
import com.SmartCampus.smartcampusmobileapp.Model.WaterLevel
import com.SmartCampus.smartcampusmobileapp.R

class WaterLevelAdapter(private val context: Context, private val arrayList: ArrayList<WaterLevel>) : BaseAdapter() {
    private lateinit var createdAt: TextView
    private lateinit var entryId: TextView
    private lateinit var field1: TextView
    private lateinit var field2: TextView
    private lateinit var field3: TextView

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
        convertView = LayoutInflater.from(context).inflate(R.layout.water_level_listview_item, parent, false)
        createdAt = convertView.findViewById(R.id.textView1)
        entryId = convertView.findViewById(R.id.textView2)
        field1 = convertView.findViewById(R.id.textView3)
        field2 = convertView.findViewById(R.id.textView4)
        field3 = convertView.findViewById(R.id.textView5)
        entryId.text = "Entry Id: "+arrayList[position].entryId
        createdAt.text = "Created At: "+arrayList[position].createdAt
        field1.text ="Field 1 Data: "+ arrayList[position].field1
        field2.text ="Field 2 Data: "+ arrayList[position].field2
        field3.text ="Field 3 Data: "+ arrayList[position].field3
        return convertView
    }
}