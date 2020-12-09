package mm.com.fairway.hospitalguide.ui.packages

data class Package(
    val hospital: Hospital,
    val package_description: String,
    val package_id: Int,
    val package_image: String,
    val package_name: String,
    val regular_price: String,
    val special_price: String
)