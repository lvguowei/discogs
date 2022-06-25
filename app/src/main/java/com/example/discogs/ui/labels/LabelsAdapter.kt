package com.example.discogs.ui.labels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.discogs.R
import com.example.discogs.usecases.models.LabelModel

class LabelsAdapter(val onLabelClicked: (LabelModel) -> Unit) :
  RecyclerView.Adapter<LabelsAdapter.ViewHolder>() {

  private var data: MutableList<LabelModel> = mutableListOf()

  fun setData(labels: List<LabelModel>) {
    data.clear()
    data.addAll(labels)
    notifyDataSetChanged()
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
    val thumb: ImageView = itemView.findViewById(R.id.thumb)
    val releases: TextView = itemView.findViewById(R.id.releases)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_label, parent, false)
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val label = data[position]
    with(holder) {
      name.text = label.name
      releases.text = label.numberOfReleases.toString()
      Glide.with(thumb).load(label.thumb).into(thumb)
      itemView.setOnClickListener {
        onLabelClicked(label)
      }
    }
  }

  override fun getItemCount() = data.size
}
