package com.example.biletkitapcigi.Adapter

import android.media.RouteListingPreference.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.biletkitapcigi.R
import com.example.biletkitapcigi.databinding.ItemTimeBinding
import com.google.firebase.database.core.Context

class TimeAdapter(private var timeSlots:List<String>):RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {
    private var selectedPosition= -1
    private var lastSelectedPosition = -1


    inner class TimeViewHolder(private var binding: ItemTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(time:String){
            binding.textViewTime.text=time
            if (selectedPosition==position){
                binding.textViewTime.setBackgroundResource(R.color.white)
                binding.textViewTime.setTextColor(ContextCompat.getColor(itemView.context,R.color.black))

            }else{
                binding.textViewTime.setBackgroundResource(R.drawable.light_black_bg)
                binding.textViewTime.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
            }
            binding.root.setOnClickListener{
                val position =  position
                if(position!=RecyclerView.NO_POSITION){
                    lastSelectedPosition=selectedPosition
                    selectedPosition=position
                    notifyItemChanged(lastSelectedPosition)
                    notifyItemChanged(selectedPosition)
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeAdapter.TimeViewHolder {
        return TimeViewHolder(ItemTimeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TimeAdapter.TimeViewHolder, position: Int) {
        holder.bind(timeSlots[position])
    }

    override fun getItemCount(): Int {
        return timeSlots.size
    }
}