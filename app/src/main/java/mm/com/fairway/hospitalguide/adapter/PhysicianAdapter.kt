package mm.com.fairway.hospitalguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.ui.physician.Physician

class PhysicianAdapter(var physicianList:ArrayList<Physician> = ArrayList()):RecyclerView.Adapter<PhysicianAdapter.PhysicianViewHolder>() {
    class  PhysicianViewHolder(item: View):RecyclerView.ViewHolder(item){
        fun bind(physician: Physician){

        }
    }
    fun updatePhysician(physicianList:ArrayList<Physician>){
        this.physicianList= physicianList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhysicianViewHolder {
       return PhysicianViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.physician_item_layount,parent,false))
    }

    override fun getItemCount(): Int {
        return physicianList.size
    }

    override fun onBindViewHolder(holder: PhysicianViewHolder, position: Int) {
        holder.bind(physicianList.get(position))
    }
}