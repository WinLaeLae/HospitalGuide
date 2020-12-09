package mm.com.fairway.hospitalguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.specility_item_layout.view.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.api.HospitalAPIClient
import mm.com.fairway.hospitalguide.ui.specilities.Speciality

class SpecilityAdapter(var specilityList:ArrayList<Speciality> = ArrayList()):RecyclerView.Adapter<SpecilityAdapter.SpecilityViewHolder>() {
    inner class SpecilityViewHolder(item: View): RecyclerView.ViewHolder(item){
        fun bind(specility :Speciality){
            itemView.specialityName_txt.text = specility.speciality_mname
            itemView.specialityEngName_txt.text = specility.speciality_ename
            Picasso.get().load(HospitalAPIClient.imageUrl + specility.speciality_image).into(itemView.speciality_img)

        }
    }
fun updateSpecilityList(specilityList:ArrayList<Speciality>){
        this.specilityList = specilityList
    notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecilityViewHolder {

        return SpecilityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.specility_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return specilityList.size
    }

    override fun onBindViewHolder(holder: SpecilityViewHolder, position: Int) {
        holder.bind(specilityList.get(position))
    }
}