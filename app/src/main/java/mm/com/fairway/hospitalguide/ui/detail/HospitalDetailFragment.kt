package mm.com.fairway.hospitalguide.ui.detail

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hospital_detail.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.api.HospitalAPIClient
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel


class HospitalDetailFragment : Fragment() {
    private lateinit var detailViewModel: HospitalGuideViewModel
    var facebookUrl = ""

    var phNo = arrayOf<String>(
        "01505284", "013581329", "013684324",
        "012300502", "019666141", "01500100", "019376200", "012304999",
        "013551355", "01317617", "01656176", "01541457"
    )
    var lat = arrayOf<Double>(
        16.842164, 16.861037, 16.840776, 16.779549, 16.877756,
        16.832970, 16.812904, 16.798233, 16.832422, 16.821268, 16.884822, 16.811245
    )
    var lon = arrayOf<Double>(
        96.136740, 96.209256, 96.089117, 96.139875, 96.129934, 96.130603,
        96.175839, 96.131235, 96.179443, 96.123195, 96.155071, 96.166340
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataItem = arguments?.let {
            HospitalDetailFragmentArgs.fromBundle(it)
        }
        var id = dataItem!!.ID

        detailViewModel = ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        detailViewModel.setDetailHospital(id)
        observeDetailHospital()
        phone_linear.setOnClickListener {
            getPhoneNo(id)
        }
        address_linear.setOnClickListener {
            getLatLon(id)
        }
        packages_btn.setOnClickListener {
            findNavController().navigate(
                HospitalDetailFragmentDirections.actionHospitalDetailFragmentToBtnEventFragment("packages", id))
        }
        specialities_btn.setOnClickListener {
            findNavController().navigate(HospitalDetailFragmentDirections.actionHospitalDetailFragmentToBtnEventFragment("speciality",id))
        }
        physicians_btn.setOnClickListener {
            findNavController().navigate(HospitalDetailFragmentDirections.actionHospitalDetailFragmentToBtnEventFragment("physicians",id))
        }
        recommet_btn.setOnClickListener {
            findNavController().navigate(HospitalDetailFragmentDirections.actionHospitalDetailFragmentToBtnEventFragment("recomment",id))
        }
        schedule_btn.setOnClickListener {
            findNavController().navigate(HospitalDetailFragmentDirections.actionHospitalDetailFragmentToBtnEventFragment("schedule",id))

        }

    }

    fun getLatLon(hospital_ID: Int) {
        var lat: Double
        var lon: Double
        when(hospital_ID){
            in 1..12 ->{
               lat = this.lat.get(hospital_ID-1)
                lon = this.lon.get(hospital_ID-1)

            }
            else -> {
                Toast.makeText(context,"Can't show hospital's location",Toast.LENGTH_LONG).show()
            }
        }
    }

    val TELEPHONE_SCHEMA = " tel:"
    val PRESERVED_CHARACTER = "+"
    val COUNTRY_CODE = "95"
    private var phCode = ""

    fun phoneCall(phoneNo: Long) {
        phCode = "1"
        val phoneCallUri =
            Uri.parse(
                TELEPHONE_SCHEMA + PRESERVED_CHARACTER + COUNTRY_CODE + phCode + phoneNo
            )
        val phoneCallIntent = Intent(Intent.ACTION_DIAL).also {
            it.setData(phoneCallUri)
        }
        startActivity(phoneCallIntent)
    }

    fun getPhoneNo(hospital_ID: Int) {
        var str: String
        var ph: Long
        when (hospital_ID) {
            in 1..12 -> {
                str = phNo.get(hospital_ID - 1)
                if (str.length == 8) {
                    ph = str.takeLast(6).toLong()
                    phoneCall(ph)
                } else {
                    ph = str.takeLast(str.length - 2).toLong()
                    phoneCall(ph)
                }
            }
            else -> {
                var builder = AlertDialog.Builder(context)
                builder.apply {
                    setTitle("Can't Call Phone")
                    setMessage(
                        "Can't call!"
                    )
                    setIcon(android.R.drawable.ic_menu_call)
                    // setPositiveButton("OK"){}

                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }


        }


    }

    fun observeDetailHospital() {
        detailViewModel.getDetailHospital()
            .observe(viewLifecycleOwner, Observer<HospitalDetailModel> { hospital ->

                Picasso.get().load(HospitalAPIClient.imageUrl + hospital.hospital.hospital_image)
                    .into(detail_logo)
                Picasso.get().load(HospitalAPIClient.imageUrl + hospital.hospital.hospital_banner)
                    .into(banner_Img)
                phoneNo_txt.text = hospital.hospital.phone_no
                hospitalAddress_txt.text = hospital.hospital.place
                email.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_EMAIL, hospital.hospital.email)
                    intent.type = " Message/rfc822"
                    startActivity(Intent.createChooser(intent, "Select email"))
                }
                weblink.setOnClickListener {
                    findNavController().navigate(
                        HospitalDetailFragmentDirections.actionHospitalDetailFragmentToHosptalWebLinkFragment(
                            hospital.hospital.website_link
                        )
                    )
                }
                facebook.setOnClickListener {
                    findNavController().navigate(
                        HospitalDetailFragmentDirections.actionHospitalDetailFragmentToHosptalWebLinkFragment(
                            hospital.hospital.facebook_link
                        )
                    )
                }


            })

    }


}