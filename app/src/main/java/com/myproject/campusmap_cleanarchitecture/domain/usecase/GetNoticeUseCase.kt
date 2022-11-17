package com.myproject.campusmap_cleanarchitecture.domain.usecase

import android.util.Log
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import javax.inject.Inject

class GetNoticeUseCase @Inject constructor() {

    private lateinit var doc: Document
    private lateinit var docArray: ArrayList<Document>
    private lateinit var notice: Elements
    private lateinit var noticeDocArray: ArrayList<Elements>
    private var noticeArray: ArrayList<NoticeItem> = ArrayList(emptyList())

    suspend operator fun invoke(url: String , linkUrl: String) : ArrayList<NoticeItem> {

        val a = CoroutineScope(Dispatchers.IO).async {

            docArray = ArrayList(emptyList())
            noticeDocArray = ArrayList(emptyList())
            notice = Elements()

            val urlCount = 20

            launch(Dispatchers.IO) {
                try {
                    // 원래 이 for문 launch로 감싸면 공지사항이 안떴는데 왜그런거지??
                    for (i in 0 until 30) {
                        doc = Jsoup.connect(url + (urlCount * i).toString()).get() // URL 웹사이트에 있는 html 코드를 끌어온다.
                        docArray.add(doc) // 그 코드들을 배열에 저장함.
                        notice = docArray[i].select("#jwxe_main_content")
                            .select(".b-top-box") // notice 속성에 끌어왔던 html 코드에서 배열 순서대로 select 부분들을 끌고온다.
                        noticeDocArray.add(notice) // notice에서 select로 끌고온 쿼리 부분들을 noticeDocArray에 저장함. 그니까 즉 =0에서 저부분이 noticeDocArray[0]에, =20이 noticeDocArray[1]에 들어가고 그런식.
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.d("NoticeFragment", "IO Error") // 에러처리 해야함.
                }
            }.join()

                launch(Dispatchers.Default) {


                    try {
                        for (i in 0 until notice.size) {

                            val itemNoticeBoxes =
                                noticeDocArray[i].select(".b-num-box").select(".b-notice-box")
                            val itemNumbers = noticeDocArray[i].select(".b-num-box")
                            val itemTitles = noticeDocArray[i].select(".b-title")
                            val itemInfos = noticeDocArray[i].select(".b-info-box")
                            val itemWriters = noticeDocArray[i].select(".b-m-con").select(".b-writer")

                            if (i == 0) {
                                for (k in 0 until itemNumbers.size) {

                                    val itemNumber: Element = itemNumbers[k]
                                    val itemTitle: Element = itemTitles[k]
                                    val itemInfo: Element = itemInfos[k]
                                    val itemLink: String =
                                        linkUrl + itemTitle.select("a").attr("href")
                                    val itemWriter = itemWriters[k]

                                    noticeArray.add(
                                        NoticeItem(
                                            itemNumber.text(),
                                            itemTitle.text(),
                                            itemInfo.text().substring(0, 10),
                                            itemLink,
                                            itemWriter.text()
                                        )
                                    )
                                }
                            } else {
                                for (k in itemNoticeBoxes.size until itemNumbers.size) {

                                    val itemNumber: Element = itemNumbers[k]
                                    val itemTitle: Element = itemTitles[k]
                                    val itemInfo: Element = itemInfos[k]
                                    val itemLink: String =
                                        linkUrl + itemTitle.select("a").attr("href")
                                    val itemWriter = itemWriters[k]

                                    noticeArray.add(
                                        NoticeItem(
                                            itemNumber.text(),
                                            itemTitle.text(),
                                            itemInfo.text().substring(0, 10),
                                            itemLink,
                                            itemWriter.text()
                                        )
                                    )
                                }
                            }
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        Log.d("NoticeFragment", "IO Error") // 에러처리 해야함.
                    }
                }.join()
            noticeArray
        }
        return a.await()
    }
}

