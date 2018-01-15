package app.xiaobaitu.readhub.model
import com.google.gson.annotations.SerializedName


/**
 * Created by baitu on 2018/1/15.
 * 科技动态Bean
 */
data class NewsInfo(
		@SerializedName("data") val data: List<Data>,
		@SerializedName("pageSize") val pageSize: Int, //20
		@SerializedName("totalItems") val totalItems: Int, //39657
		@SerializedName("totalPages") val totalPages: Int //1983
){
    data class Data(
            @SerializedName("id") val id: Int, //18535547
            @SerializedName("title") val title: String, //新提案将禁止美国政府使用华为和中兴手机
            @SerializedName("summary") val summary: String, //为此，得克萨斯州议员迈克·科纳威（Mike Conaway）上周提出一项名为“保卫美国政府通讯法案”的新提案，旨在禁止美国政府机构使用这些公司的手机和设备 ... Conaway的提案将禁止美国政府购买和使用华为和中兴的“通讯设备或服务” ... 这个新提案将是华为面临的另一个令人头痛的问题，上周，该公司与AT＆T的合作关系突然流产，促使该公司首席执行官在CES展会上进行脱稿演讲。
            @SerializedName("summaryAuto") val summaryAuto: String, //为此，得克萨斯州议员迈克·科纳威（Mike Conaway）上周提出一项名为“保卫美国政府通讯法案”的新提案，旨在禁止美国政府机构使用这些公司的手机和设备 ... Conaway的提案将禁止美国政府购买和使用华为和中兴的“通讯设备或服务” ... 这个新提案将是华为面临的另一个令人头痛的问题，上周，该公司与AT＆T的合作关系突然流产，促使该公司首席执行官在CES展会上进行脱稿演讲。
            @SerializedName("url") val url: String, //http://www.cnbeta.com/articles/tech/689317.htm
            @SerializedName("mobileUrl") val mobileUrl: String, //http://www.cnbeta.com/articles/tech/689317.htm
            @SerializedName("siteName") val siteName: String, //cnBeta
            @SerializedName("siteSlug") val siteSlug: String, //rss_cnBeta
            @SerializedName("language") val language: String, //zh-cn
            @SerializedName("authorName") val authorName: String, //vivian
            @SerializedName("publishDate") val publishDate: String //2018-01-15T04:04:33.000Z
    )
}

