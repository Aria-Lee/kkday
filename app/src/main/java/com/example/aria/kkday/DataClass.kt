package com.example.aria.kkday

import android.content.Context

class DetailData(var imageId: Int, var name: String, var location: String, var rating: Int, var ratingCount: String, var count: String)
class SimpleData(var imageId: Int, var name: String)

class Data(var context: Context) {
    private var titleList = mutableListOf("最近瀏覽", "精選城市", "春日櫻花 浪漫獻映", "為你推薦")

    private var detailDataNameList = mutableListOf(
            "【2019 京都賞櫻必備】嵐山小火車保證有位限量車票（季節限定）",
            "【韓國賞櫻推薦】首爾出發鎮海軍港節一日遊",
            "【東京巴士一日遊】箱根海盜船、小田原城、大湧谷、蘆之湖（保證有位）",
            "【東京巴士一日遊】富士山、河口湖、忍野八海、御殿場 Outlet（保證有位）",
            "【台南藝術文化村】十鼓仁糖文創園區入場門票",
            "【台南高鐵出發】台灣高鐵票 8 折優惠（外國人限定電子票）",
            "【台灣租車推薦】五人座轎車 / 七人座商務車租車自駕",
            "【台南出發】台灣高鐵兩人同行一人免費電子車票（外國人限定）"
    )

    private var detailDataLocationList = mutableListOf(
            "日本, 京都",
            "韓國, 首爾, 多個城市",
            "日本, 東京, 多個城市",
            "日本, 東京",
            "台灣, 台南",
            "台灣, 台南",
            "台灣, 台北, 多個城市",
            "台灣, 台南"
    )

    private var detailDataRatingList = mutableListOf(4, 4, 5, 5, 5, 5, 5, 5)

    private var detailDataRatingCountList = mutableListOf(
            "(146)",
            "(42)",
            "(78)",
            "(907)",
            "(28)",
            "(693)",
            "(97)",
            "(40)"
    )

    private var detailDataCountList = mutableListOf(
            "360",
            "1,330",
            "1,605",
            "1,584",
            "350",
            "112",
            "1,399",
            "112"
    )

    private var simpleDataNameList = mutableListOf(
            "首爾",
            "紐約",
            "東京",
            "香港",
            "沖繩",
            "巴黎"
    )

    fun getTitleList(): MutableList<String> {
        return titleList
    }

    fun getSimpleDataList(): MutableList<SimpleData> {
        var simpleDataList = mutableListOf<SimpleData>()
        for (i in 0 .. 5) {
            val name = "simple_img${i + 1}"
            val id = context.resources.getIdentifier(name, "drawable", context.packageName)
            simpleDataList.add(SimpleData(id, simpleDataNameList[i]))
        }

        return simpleDataList
    }

    fun getDetailDataList(): MutableList<DetailData> {
        var detailDataList = mutableListOf<DetailData>()
        var name : String
        var id: Int
        for (i in 0 .. 7) {
            name = "img${i + 1}"
            id = context.resources.getIdentifier(name, "drawable", context.packageName)
            detailDataList.add(
                    DetailData(
                            id,
                            detailDataNameList[i],
                            detailDataLocationList[i],
                            detailDataRatingList[i],
                            detailDataRatingCountList[i],
                            detailDataCountList[i]))
        }

        return detailDataList
    }

    fun getDetailContentImgList(): MutableList<Int>{
        var detailContentImgList = mutableListOf<Int>()
        var name = "img1"
        var id = context.resources.getIdentifier(name, "drawable", context.packageName)
        detailContentImgList.add(id)
        for(i in 2 .. 9){
            name = "img1_$i"
            id = context.resources.getIdentifier(name, "drawable", context.packageName)
            detailContentImgList.add(id)
        }
        return detailContentImgList
    }


}