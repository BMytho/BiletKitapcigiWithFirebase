package com.example.biletkitapcigi.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.biletkitapcigi.Activity.SeatListActivity
import com.example.biletkitapcigi.Models.Seat
import com.example.biletkitapcigi.R
import com.example.biletkitapcigi.databinding.SeatItemBinding
import com.google.firebase.database.core.Context

private fun Context.getColor(white: Int): Any {
    return white
}

private fun TextView.setTextColor(color: Any) {

}

class SeatListAdapter(
    private val seatList:List<Seat>,
    @SuppressLint("RestrictedApi") private val context: SeatListActivity,
    private val selectedSeat:SelectedSeat
): RecyclerView.Adapter<SeatListAdapter.SeatViewHolder>() {
    private val selectedSeatName=ArrayList<String>()

    class SeatViewHolder(val binding: SeatItemBinding):RecyclerView.ViewHolder(binding.root) {

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeatListAdapter.SeatViewHolder {
        return SeatViewHolder(SeatItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
       val seat=seatList[position]
        holder.binding.seat.text=seat.name

        when(seat.status){
            Seat.SeatStatus.AVAILABLE->{
                holder.binding.seat.setBackgroundResource(R.drawable.ic_seat_available)
                holder.binding.seat.setTextColor(context.getColor(R.color.white))
            }
            Seat.SeatStatus.SELECTED->{
                holder.binding.seat.setBackgroundResource(R.drawable.ic_seat_selected)
                holder.binding.seat.setTextColor(context.getColor(R.color.black))
            }
            Seat.SeatStatus.UNAVAILABLE->{
                holder.binding.seat.setBackgroundResource(R.drawable.ic_seat_unavailable)
                holder.binding.seat.setTextColor(context.getColor(R.color.gray))
            }
        }
        holder.binding.seat.setOnClickListener{
            when(seat.status){
                Seat.SeatStatus.AVAILABLE->{
                    seat.status=Seat.SeatStatus.SELECTED
                    selectedSeatName.add(seat.name)
                    notifyItemChanged(position)
                }
                Seat.SeatStatus.SELECTED->{
                    seat.status=Seat.SeatStatus.AVAILABLE
                    selectedSeatName.add(seat.name)
                    notifyItemChanged(position)
                }
                else->{}

            }
            val selected = selectedSeatName.joinToString (",")
            selectedSeat.Return(selected,selectedSeatName.size)
        }
    }

    override fun getItemCount(): Int {
        return seatList.size
    }

    interface SelectedSeat{
        fun Return(selectedName:String,num:Int)
    }

}