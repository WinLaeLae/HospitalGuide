package mm.com.fairway.hospitalguide.api

import mm.com.fairway.hospitalguide.ui.detail.HospitalDetailModel
import mm.com.fairway.hospitalguide.ui.hospital.HospitalListModel
import mm.com.fairway.hospitalguide.ui.packages.PackagesListModel
import mm.com.fairway.hospitalguide.ui.physician.PhysicianListModel
import mm.com.fairway.hospitalguide.ui.schedule.ScheduleListModel
import mm.com.fairway.hospitalguide.ui.specilities.SpecialitiesListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HospitalApiInterface {
    @GET("hospital")
    fun getHospitalList(): Call<HospitalListModel>

    @GET("hospital/{id}")
    fun getDetailHospital(
        @Path("id") hospital_id: Int
    ): Call<HospitalDetailModel>

    @GET("package")
    fun getPackageList(): Call<PackagesListModel>

    @GET("package")
    fun getHospitalPackages(
        @Query("hospital") hospital_id: Int
    ): Call<PackagesListModel>

    @GET("speciality")
    fun getSpecialityList(): Call<SpecialitiesListModel>

    @GET("physician")
    fun getPhysician(): Call<PhysicianListModel>

    @GET("schedule")
    fun getScheduleList(): Call<ScheduleListModel>
}