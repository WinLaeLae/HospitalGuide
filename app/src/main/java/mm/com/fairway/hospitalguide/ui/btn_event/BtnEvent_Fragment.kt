package mm.com.fairway.hospitalguide.ui.btn_event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_btn_event_.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.adapter.PackagesAdapter
import mm.com.fairway.hospitalguide.adapter.SpecilityAdapter
import mm.com.fairway.hospitalguide.ui.packages.Package
import mm.com.fairway.hospitalguide.ui.specilities.Speciality
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel


class BtnEvent_Fragment : Fragment() {
    private lateinit var  viewModel: HospitalGuideViewModel

var hospitalName= arrayOf<String>("ကန်သာယာဆေးရုံ",
    "ပင်လုံဆေးရုံ","ပန်းလှိုင်ဆေးရုံ","ဗဟိုစည်ဆေးရုံ","ဝိတိုရိယဆေးရုံ",
    "သုခကမ္ဘာဆေးရုံ","အာယု အပြည်ပြည်ဆိုင်ရာဆေးရုံ","အာရှတော်ဝင်ဆေးရုံ","အောင်ရတနာဆေးရုံ",
    "Grand Hantha ဆေးရုံ", "OSC ဆေးရုံ","SSC ဆေးရုံ")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_btn_event_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataItem = arguments?.let {
            BtnEvent_FragmentArgs.fromBundle(it)
        }

        viewModel= ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        var id = dataItem!!.id
        var strBtn= dataItem.btnName
        hospName_txt.text= hospitalName.get(id-1)
        when(strBtn)
        {
            "packages" -> {

                viewModel.setHospitalPackages(id)
                var packagesAdapter = PackagesAdapter()
                recyclerView_btn_event.apply {
                    layoutManager = GridLayoutManager(context,2)
                    adapter= packagesAdapter
                }
                viewModel.getHospitalPackages().observe(viewLifecycleOwner, Observer {  hospitalPackage->
                    packagesAdapter.updatePackageList( hospitalPackage.packages as
                    ArrayList<Package>)
                })

            }
            "speciality" -> {
                viewModel.setDetailHospital(id)
                var specialityAdapter = SpecilityAdapter()
                recyclerView_btn_event.apply {
                    layoutManager = GridLayoutManager(context,2)
                    adapter = specialityAdapter
                }
                viewModel.getDetailHospital()
                    .observe(viewLifecycleOwner, Observer {  speciality->
                        specialityAdapter.updateSpecilityList(speciality.hospital.specialities as ArrayList<Speciality>)
                    })
            }
            "physicians" -> {}
            "recomment" -> {}
            "schedule" -> {}
        }
    }
}