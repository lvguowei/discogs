package com.example.discogs.ui.releases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.discogs.R
import com.example.discogs.usecases.models.ReleaseModel


class ReleasesAdapter :
  RecyclerView.Adapter<ReleasesAdapter.ViewHolder>() {

  private var data: MutableList<ReleaseModel> = mutableListOf()

  fun setData(labels: List<ReleaseModel>) {
    data.clear()
    data.addAll(labels)
    notifyDataSetChanged()
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val year: TextView = itemView.findViewById(R.id.year)
    val thumb: ImageView = itemView.findViewById(R.id.thumb)
    val want: TextView = itemView.findViewById(R.id.want)
    val have: TextView = itemView.findViewById(R.id.have)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_release, parent, false)
    )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val release = data[position]
    with(holder) {
      title.text = release.title
      want.text = release.want.toString()
      year.text = release.year
      have.text = release.have.toString()
      Glide.with(thumb).load(release.thumb).into(thumb)
    }
  }

  override fun getItemCount() = data.size
}
