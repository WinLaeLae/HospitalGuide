package mm.com.fairway.hospitalguide.ui.physician

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_physician.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.adapter.PhysicianAdapter
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel

class PhysicianFragment : Fragment() {

    private lateinit var physicianViewModel: HospitalGuideViewModel
    private lateinit var physicianAdapter: PhysicianAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_physician, container, false)

    }

    override fun onResume() {
        super.onResume()
        physicianViewModel.setPhysicianList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        physicianViewModel = ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        physicianAdapter = PhysicianAdapter()
        recyclerView_physician.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = physicianAdapter
        }
        observePhysician()
    }

    fun observePhysician() {
        physicianViewModel.getPhysicianList().observe(viewLifecycleOwner, Observer { physician ->
            physicianAdapter.updatePhysician(physician.physicians as ArrayList<Physician>)
        })
    }
}