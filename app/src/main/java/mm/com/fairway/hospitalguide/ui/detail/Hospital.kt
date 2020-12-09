package mm.com.fairway.hospitalguide.ui.detail

data class Hospital(
    val email: String,
    val facebook_link: String,
    val hospital_banner: String,
    val hospital_id: Int,
    val hospital_image: String,
    val hospital_name: String,
    val phone_no: String,
    val place: String,
    val specialities: List<Any>,
    val website_link: String
)