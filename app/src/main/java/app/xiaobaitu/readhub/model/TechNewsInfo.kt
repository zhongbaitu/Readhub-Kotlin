package app.xiaobaitu.readhub.model

import com.google.gson.annotations.SerializedName


/**
 * Created by baitu on 2018/1/15.
 * 开发者咨询Bean
 */
data class TechNewsInfo(
        @SerializedName("data") val data: List<Data>,
        @SerializedName("pageSize") val pageSize: Int, //20
        @SerializedName("totalItems") val totalItems: Int, //3920
        @SerializedName("totalPages") val totalPages: Int //196
) {
    data class Data(
            @SerializedName("id") val id: Int, //18535823
            @SerializedName("title") val title: String, //算法题：交错正负数
            @SerializedName("summary") val summary: String, //给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
            @SerializedName("summaryAuto") val summaryAuto: String, //给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
            @SerializedName("url") val url: String, //http://mp.weixin.qq.com/s?__biz=MzI1MTIzMzI2MA==&mid=2650562239&idx=3&sn=8b3bdd5292d283adbab965508b32c0dd&chksm=f1fee83cc689612a9a3490ee4ebf9d8c56abb9e3bbf7169a74441bed64f40c5f818ebc950d23&scene=27
            @SerializedName("mobileUrl") val mobileUrl: String, //http://mp.weixin.qq.com/s?__biz=MzI1MTIzMzI2MA==&mid=2650562239&idx=3&sn=8b3bdd5292d283adbab965508b32c0dd&chksm=f1fee83cc689612a9a3490ee4ebf9d8c56abb9e3bbf7169a74441bed64f40c5f818ebc950d23&scene=27
            @SerializedName("siteName") val siteName: String, //算法爱好者
            @SerializedName("language") val language: String, //zh-cn
            @SerializedName("authorName") val authorName: Any, //null
            @SerializedName("publishDate") val publishDate: String //2018-01-15T04:44:10.530Z
    )
}