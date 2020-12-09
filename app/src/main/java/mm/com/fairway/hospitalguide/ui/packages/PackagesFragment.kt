package mm.com.fairway.hospitalguide.ui.packages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_packages.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.adapter.PackagesAdapter
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel

class PackagesFragment : Fragment() {
private  lateinit var  packViewModel : HospitalGuideViewModel
    private lateinit var packAdapter: PackagesAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_packages, container, false)

        }

    override fun onResume() {
        super.onResume()
        packViewModel.setPackageList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        packViewModel= ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        packAdapter= PackagesAdapter()

        recyclerView_packages.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter= packAdapter

        }
        observePackages()
    }
    private fun observePackages(){
     packViewModel.getPackageList().observe(viewLifecycleOwner, Observer { packagesList->
         packAdapter.updatePackageList(packagesList.packages as ArrayList<Package>)
     })
    }

}