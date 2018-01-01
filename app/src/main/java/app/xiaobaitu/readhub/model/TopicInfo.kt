package app.xiaobaitu.readhub.model
import com.google.gson.annotations.SerializedName



/**
 * Created by baitu on 18/1/1.
 * 热门话题 bean
 */
data class TopicInfo(
        @SerializedName("data") val data: List<Data>,
        @SerializedName("pageSize") val pageSize: Int, //20
        @SerializedName("totalItems") val totalItems: Int, //10151
        @SerializedName("totalPages") val totalPages: Int //508
)

data class Data(
        @SerializedName("id") val id: String, //2dUtej1mvN3
        @SerializedName("createdAt") val createdAt: String, //2018-01-01T07:21:14.000Z
        @SerializedName("nelData") val nelData: NelData,
        @SerializedName("eventData") val eventData: EventData,
        @SerializedName("newsArray") val newsArray: List<NewsArray>,
        @SerializedName("order") val order: Int, //31004
        @SerializedName("publishDate") val publishDate: String, //2018-01-01T07:21:14.027Z
        @SerializedName("summary") val summary: String, //该公司已经宣布与TapTapComics合作发布名为Marvel：CreateYourOwn的新应用程序，该应用程序将允许用户选择超级英雄，制作超级英雄，添加自己的文字和对话框 ... 不幸的是，Marvel：Create Your Own的用户会发现，他们不是很自由地制作漫画 ... 不允许创作的内容包括可能让年幼的孩子或幼儿的父母惊吓的内容，耸人听闻（杀人蜂，八卦，外星人，丑闻等）的内容，猥亵，不良或冒犯性的语言， 处方药或非处方药，维生素和膳食补充剂，死亡内容，政治内容和其他有争议的话题（社会问题等）。
        @SerializedName("title") val title: String, //Marvel提供漫画应用程序让用户自己制作漫画
        @SerializedName("updatedAt") val updatedAt: String, //2018-01-01T07:21:14.345Z
        @SerializedName("timeline") val timeline: Any, //null
        @SerializedName("extra") val extra: Extra
)

data class NewsArray(
		@SerializedName("id") val id: Int, //18430183
		@SerializedName("url") val url: String, //http://www.ifanr.com/962895
		@SerializedName("title") val title: String, //漫威推出漫画创作应用，但规则太“迪士尼”了
		@SerializedName("groupId") val groupId: Int, //1
		@SerializedName("siteName") val siteName: String, //爱范儿
		@SerializedName("siteSlug") val siteSlug: String, //site_ifanr
		@SerializedName("mobileUrl") val mobileUrl: String, //http://www.ifanr.com/962895
		@SerializedName("authorName") val authorName: String, //方嘉文
		@SerializedName("duplicateId") val duplicateId: Int, //1
		@SerializedName("publishDate") val publishDate: String //2018-01-01T07:10:45.000Z
)

data class Extra(
		@SerializedName("instantView") val instantView: Boolean //false
)

data class NelData(
		@SerializedName("state") val state: Boolean, //true
		@SerializedName("result") val result: List<Result>
//		@SerializedName("nerResult") val nerResult: NerResult
)

//data class NerResult(
//		@SerializedName("person") val person: Person,
//		@SerializedName("company") val company: Company,
//		@SerializedName("product") val product: Product,
//		@SerializedName("location") val location: Location
//)

//data class Company(
//		@SerializedName("Marvel") val marvel: Marvel,
//		@SerializedName("漫威") val 漫威: 漫威
//)
//
//data class 漫威(
//		@SerializedName("weight") val weight: Double //0.1817288100719452
//)
//
//data class Marvel(
//		@SerializedName("weight") val weight: Double //0.17293965816497803
//)

//data class Location(
//)
//
//data class Person(
//)
//
//data class Product(
//)

data class Result(
		@SerializedName("weight") val weight: Double, //0.17293965816497803
		@SerializedName("nerName") val nerName: String, //Marvel
		@SerializedName("entityId") val entityId: Int, //225310
		@SerializedName("entityName") val entityName: String, //MARVEL
		@SerializedName("entityType") val entityType: String, //company
		@SerializedName("fromAlgorithm") val fromAlgorithm: Boolean, //true
		@SerializedName("entityUniqueId") val entityUniqueId: String //baike_2142763
)

data class EventData(
		@SerializedName("result") val result: List<Any>
)