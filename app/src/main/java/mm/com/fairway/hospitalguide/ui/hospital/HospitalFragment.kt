package mm.com.fairway.hospitalguide.ui.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_hospital.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.adapter.HospitalAdapter
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel

class HospitalFragment : Fragment() ,HospitalAdapter.ClickListener{

private lateinit var hospitalViewModel : HospitalGuideViewModel
    private lateinit var  hospitalAdapter: HospitalAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

       return inflater.inflate(R.layout.fragment_hospital, container, false)

    }

    override fun onResume() {
        super.onResume()
hospitalViewModel.setHospitalList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hospitalAdapter = HospitalAdapter()
        hospitalViewModel = ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        recyclerView_hospital.apply {
            layoutManager= GridLayoutManager(context,3)
            adapter= hospitalAdapter
        }
        hospitalAdapter.setOnClickListener(this)
        observeHospitalList()
    }

    private fun observeHospitalList(){
        hospitalViewModel.getHospital().observe(viewLifecycleOwner, Observer {hospital ->
            hospitalAdapter.updateHospitalList(hospital.hospitals as ArrayList<Hospital>)
        })
    }

    override fun onClick(hospital: Hospital) {
findNavController().navigate(HospitalFragmentDirections.actionNavigationHospitalToHospitalDetailFragment(hospital.hospital_id))
    }
}