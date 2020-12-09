package mm.com.fairway.hospitalguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.packages_item_layout.view.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.api.HospitalAPIClient
import mm.com.fairway.hospitalguide.ui.packages.Package

class PackagesAdapter(var packList : ArrayList<Package> = ArrayList()) : RecyclerView.Adapter<PackagesAdapter.PackagesViewHolder>(){
    inner  class  PackagesViewHolder(item : View): RecyclerView.ViewHolder(item){

        fun bind(pack: Package){
            itemView.hosptal_title_txt.text= pack.hospital.hospital_name
            Picasso.get().load(HospitalAPIClient.imageUrl+ pack.package_image).into(itemView.packagesImg)
            itemView.packagesName_txt.text= pack.package_name
            itemView.packagesPrice_txt.text= pack.regular_price
                    }
    }
fun updatePackageList(packList: ArrayList<Package>){
    this. packList= packList
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagesViewHolder {
        return  PackagesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.packages_item_layout,parent,false))
    }

    override fun getItemCount(): Int {

        return packList.size
    }

    override fun onBindViewHolder(holder: PackagesViewHolder, position: Int) {
        holder.bind(packList.get(position))
    }
}