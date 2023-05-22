package com.myproject.campusmap_cleanarchitecture.data.datasource.remotedatasource

import android.util.Log
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.JsoupData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class JsoupDataSource @Inject constructor(
) {

    private lateinit var doc: Document

    suspend fun getJsoupData(url: String, linkUrl: String): ArrayList<JsoupData> {

        var notice: Elements = Elements()
        var noticeArray: ArrayList<JsoupData> = ArrayList(emptyList())
        val docArray: ArrayList<Document> = ArrayList(emptyList())
        var noticeDocArray: ArrayList<Elements> = ArrayList(emptyList())

        val urlCount = 20

        for (i in 0 until 30) {

            try {

                doc = Jsoup.connect(url + (urlCount * i).toString()).get()

                docArray.add(doc)

                notice = docArray[i].select("#jwxe_main_content").select(".b-top-box")

                noticeDocArray.add(notice)

            } catch (e: IndexOutOfBoundsException) {

                Log.d("IndexOut", e.message.toString())

            }
        }

        try {

            for (i in 0 until notice.size) {

                val noticeItemBoxes = noticeDocArray[i].select(".b-num-box").select(".b-notice-box")
                val noticeItemNumbers = noticeDocArray[i].select(".b-num-box")
                val noticeItemTitles = noticeDocArray[i].select(".b-title")
                val noticeItemInfos = noticeDocArray[i].select(".b-info-box")
                val noticeItemWriters = noticeDocArray[i].select(".b-m-con").select(".b-writer")

                if (i == 0) {

                    for (j in 0 until noticeItemNumbers.size) {

                        val noticeItemNumber = noticeItemNumbers[j]
                        val noticeItemTitle = noticeItemTitles[j]
                        val noticeItemInfo = noticeItemInfos[j]
                        val noticeItemLink = linkUrl + noticeItemTitle.select("a").attr("href")
                        val noticeItemWriter = noticeItemWriters[j]

                        noticeArray.add(
                            JsoupData(
                                noticeItemNumber.text(),
                                noticeItemTitle.text(),
                                noticeItemInfo.text(),
                                noticeItemLink,
                                noticeItemWriter.text()
                            )
                        )
                    }

                } else {

                    for (k in noticeItemBoxes.size until noticeItemNumbers.size) {

                        val noticeItemNumber: Element = noticeItemNumbers[k]
                        val noticeItemTitle: Element = noticeItemTitles[k]
                        val noticeItemInfo: Element = noticeItemInfos[k]
                        val noticeItemLink: String = linkUrl + noticeItemTitle.select("a").attr("href")
                        val noticeItemWriter = noticeItemWriters[k]

                        noticeArray.add(
                            JsoupData(
                                noticeItemNumber.text(),
                                noticeItemTitle.text(),
                                noticeItemInfo.text().substring(0, 10),
                                noticeItemLink,
                                noticeItemWriter.text()
                            )
                        )
                    }

                }

            }
        } catch (e: IndexOutOfBoundsException) {

            Log.d("IndexOut", e.message.toString())

        }
        return noticeArray
    }

}