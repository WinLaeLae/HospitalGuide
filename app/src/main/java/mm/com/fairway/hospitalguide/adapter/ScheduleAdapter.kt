package mm.com.fairway.hospitalguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.schedule_item_layout.view.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.api.HospitalAPIClient
import mm.com.fairway.hospitalguide.ui.schedule.Schedule

class ScheduleAdapter(var scheduleList:ArrayList<Schedule> = ArrayList()):RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(item: View):RecyclerView.ViewHolder(item){
        fun bind(schedule: Schedule){
Picasso.get().load(HospitalAPIClient .imageUrl + schedule.physician.speciality.speciality_image).into(itemView.schedule_img)
            itemView.date_txt.text= schedule.date_time
            itemView.doctorName_txt.text= schedule.physician.name
            itemView.title_txt.text=schedule.hospital.hospital_name
            var text = "(" + schedule.physician.speciality.speciality_ename + ")"
          itemView.specility_name_txt.text = schedule.physician.speciality.speciality_mname  + text

        }
    }
fun updateSchedule(scheduleList: ArrayList<Schedule>){
    this.scheduleList= scheduleList
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.schedule_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
      return  scheduleList.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
       holder.bind(scheduleList.get(position))
    }
}