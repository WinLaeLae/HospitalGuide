package mm.com.fairway.hospitalguide.ui.specilities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_specilities.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.adapter.SpecilityAdapter
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel

class SpecilitiesFragment : Fragment() {
private lateinit var  specilityViewModel: HospitalGuideViewModel
    private lateinit var specialityAdapter: SpecilityAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specilities, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        specialityAdapter = SpecilityAdapter()
        specilityViewModel = ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        recyclerView_specilites.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = specialityAdapter
        }
        specilityViewModel.getSpeciality().observe(viewLifecycleOwner, Observer { speciality->
            specialityAdapter.updateSpecilityList(speciality.specialities as ArrayList<Speciality>)
        })
    }
}
