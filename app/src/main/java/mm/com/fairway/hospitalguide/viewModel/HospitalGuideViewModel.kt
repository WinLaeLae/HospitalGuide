package mm.com.fairway.hospitalguide.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.hospitalguide.api.HospitalAPIClient
import mm.com.fairway.hospitalguide.ui.detail.HospitalDetailModel
import mm.com.fairway.hospitalguide.ui.hospital.HospitalListModel
import mm.com.fairway.hospitalguide.ui.packages.PackagesListModel
import mm.com.fairway.hospitalguide.ui.physician.PhysicianListModel
import mm.com.fairway.hospitalguide.ui.schedule.ScheduleListModel
import mm.com.fairway.hospitalguide.ui.specilities.SpecialitiesListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalGuideViewModel : ViewModel() {
    private var apiClient = HospitalAPIClient()
    private var packages: MutableLiveData<PackagesListModel> = MutableLiveData()
    private var hospital: MutableLiveData<HospitalListModel> = MutableLiveData()
    private var detailHospital: MutableLiveData<HospitalDetailModel> = MutableLiveData()
    private var speciality: MutableLiveData<SpecialitiesListModel> = MutableLiveData()
    private var physician: MutableLiveData<PhysicianListModel> = MutableLiveData()
    private var schedule: MutableLiveData<ScheduleListModel> = MutableLiveData()
    private var hospitalPackages :MutableLiveData<PackagesListModel> = MutableLiveData()
    fun getHospitalPackages(): LiveData<PackagesListModel> = hospitalPackages
    fun setHospitalPackages(Id: Int){
        var apiCall=apiClient.getHospitalPackageList(Id)
        apiCall.enqueue(object :Callback<PackagesListModel>{
            override fun onFailure(call: Call<PackagesListModel>, t: Throwable) {
                Log.d("HospitalPackage>>>",t.toString())
            }

            override fun onResponse(
                call: Call<PackagesListModel>,
                response: Response<PackagesListModel>
            ) {
                hospitalPackages.value= response.body()
            }

        })
    }
    fun getScheduleList(): LiveData<ScheduleListModel> = schedule
    fun setScheduleList() {
        var apiCall = apiClient.getScheduleList()
        apiCall.enqueue(object : Callback<ScheduleListModel> {
            override fun onFailure(call: Call<ScheduleListModel>, t: Throwable) {
                Log.d("Schedule>>>", t.toString())
            }

            override fun onResponse(
                call: Call<ScheduleListModel>,
                response: Response<ScheduleListModel>
            ) {
                schedule.value = response.body()
            }

        })
    }

    fun getPhysicianList(): LiveData<PhysicianListModel> = physician
    fun setPhysicianList() {
        var apiCall = apiClient.getPhysicianList()
        apiCall.enqueue(object : Callback<PhysicianListModel> {
            override fun onFailure(call: Call<PhysicianListModel>, t: Throwable) {
                Log.d("Physician>>>", t.toString())
            }

            override fun onResponse(
                call: Call<PhysicianListModel>,
                response: Response<PhysicianListModel>
            ) {
                physician.value = response.body()
            }

        })
    }

    fun getSpeciality(): LiveData<SpecialitiesListModel> = speciality
    fun setSpeciality() {
        var apiCall = apiClient.getSpecilitiesList()
        apiCall.enqueue(object : Callback<SpecialitiesListModel> {
            override fun onFailure(call: Call<SpecialitiesListModel>, t: Throwable) {
                Log.d("Speciality>>>", t.toString())
            }

            override fun onResponse(
                call: Call<SpecialitiesListModel>,
                response: Response<SpecialitiesListModel>
            ) {
                speciality.value = response.body()
            }
        })
    }

    fun getPackageList(): LiveData<PackagesListModel> = packages
    fun setPackageList() {
        var apiCall = apiClient.getPackageList()
        apiCall.enqueue(object : Callback<PackagesListModel> {
            override fun onFailure(call: Call<PackagesListModel>, t: Throwable) {
                Log.d("PackagesList>>>", t.toString())
            }

            override fun onResponse(
                call: Call<PackagesListModel>,
                response: Response<PackagesListModel>
            ) {
                packages.value = response.body()
            }

        })
    }

    fun getDetailHospital(): LiveData<HospitalDetailModel> = detailHospital
    fun setDetailHospital(id: Int) {
        var apiCall = apiClient.getHospitalDetail(id)
        apiCall.enqueue(object : Callback<HospitalDetailModel> {
            override fun onFailure(call: Call<HospitalDetailModel>, t: Throwable) {
                Log.d("Detail>>>", t.toString())
            }

            override fun onResponse(
                call: Call<HospitalDetailModel>,
                response: Response<HospitalDetailModel>
            ) {
                detailHospital.value = response.body()
            }

        })
    }

    fun getHospital(): LiveData<HospitalListModel> = hospital
    fun setHospitalList() {
        var apiCall = apiClient.getHospitalList()
        apiCall.enqueue(object : Callback<HospitalListModel> {
            override fun onFailure(call: Call<HospitalListModel>, t: Throwable) {
                Log.d("hospitalList", t.toString())
            }

            override fun onResponse(
                call: Call<HospitalListModel>,
                response: Response<HospitalListModel>
            ) {
                hospital.value = response.body()
                Log.d("hospitalResponse", response.toString())
            }
        })

    }
}