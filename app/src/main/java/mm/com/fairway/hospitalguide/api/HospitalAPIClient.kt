package mm.com.fairway.hospitalguide.api

import mm.com.fairway.hospitalguide.ui.detail.HospitalDetailModel
import mm.com.fairway.hospitalguide.ui.hospital.HospitalListModel
import mm.com.fairway.hospitalguide.ui.packages.PackagesListModel
import mm.com.fairway.hospitalguide.ui.physician.PhysicianListModel
import mm.com.fairway.hospitalguide.ui.schedule.ScheduleListModel
import mm.com.fairway.hospitalguide.ui.specilities.SpecialitiesListModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HospitalAPIClient {
    private val BASE_URL = "http://hospitalguideapi.dsv.hoz.mybluehost.me/api/"
    var apiInterface: HospitalApiInterface

    companion object {
        var imageUrl = "http://hospitalguideapi.dsv.hoz.mybluehost.me/"
    }

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(HospitalApiInterface::class.java)
    }


    fun getHospitalList(): Call<HospitalListModel> {
        return apiInterface.getHospitalList()
    }

    fun getHospitalDetail(id: Int): Call<HospitalDetailModel> {
        return apiInterface.getDetailHospital(id)
    }
    fun getPackageList():Call<PackagesListModel>{
        return  apiInterface.getPackageList()
    }
    fun getHospitalPackageList(id: Int): Call<PackagesListModel>{
        return apiInterface.getHospitalPackages(id)
    }
    fun getSpecilitiesList(): Call<SpecialitiesListModel>{
        return apiInterface.getSpecialityList()
    }
    fun getPhysicianList() : Call<PhysicianListModel>{
        return apiInterface.getPhysician()
    }
    fun getScheduleList() : Call<ScheduleListModel>{
        return apiInterface.getScheduleList()
    }
}