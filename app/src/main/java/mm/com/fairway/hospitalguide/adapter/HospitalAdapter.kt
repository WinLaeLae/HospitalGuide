package mm.com.fairway.hospitalguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hospital_item_layout.view.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.api.HospitalAPIClient
import mm.com.fairway.hospitalguide.ui.hospital.Hospital
import mm.com.fairway.hospitalguide.ui.hospital.HospitalFragment

class HospitalAdapter( var hospitalLst:ArrayList<Hospital> = ArrayList()):RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    var clickListener : ClickListener? = null
    fun setOnClickListener(clickListener: HospitalFragment){
        this. clickListener = clickListener
    }

    fun updateHospitalList(hospitalList : ArrayList<Hospital>){
        this .hospitalLst= hospitalList
        notifyDataSetChanged()
    }


    inner class  HospitalViewHolder(item:View): RecyclerView.ViewHolder(item),
    View.OnClickListener{

        lateinit var  hospital: Hospital
        init {
            item.setOnClickListener(this)
        }
        fun bind(hospital: Hospital){
            this.hospital = hospital
            Picasso.get().load(HospitalAPIClient.imageUrl + hospital.hospital_image).into(itemView.hospitalLogo_imgView)
        }

        override fun onClick(v: View?) {
           clickListener?.onClick(hospital)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hospital_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return  hospitalLst.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
      holder.bind(hospitalLst.get(position))
    }
    interface  ClickListener{
        fun onClick(hospital: Hospital)
    }
}