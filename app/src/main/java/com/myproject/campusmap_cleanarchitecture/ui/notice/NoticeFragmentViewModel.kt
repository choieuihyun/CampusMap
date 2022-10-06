package com.campusmap.android.campusmap_with_kakao.notice

import android.util.Log
import androidx.lifecycle.ViewModel
import com.campusmap.android.campusmap_with_kakao.jsoup.NoticeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class NoticeFragmentViewModel : ViewModel() { // 근데 대충 해봤는데 이건 아닌듯 ㅋㅋ
    // Repository 패턴인가 써볼까
    private lateinit var doc: Document
    private lateinit var docArray: ArrayList<Document>
    private lateinit var notice: Elements
    private lateinit var noticeDocArray: ArrayList<Elements>
    private var noticeArray: ArrayList<NoticeItem> = ArrayList(emptyList())

/*    private val url =
        "https://www.jj.ac.kr/jj/community/notice/haksanotice.jsp?mode=list&board_no=1076&pager.offset=" // 학사 공지사항 홈페이지 (1페이지)
    private var urlCount = 20
    private val linkUrl = "https://www.jj.ac.kr/jj/community/notice/haksanotice.jsp"*/

    val getNoticeItems: ArrayList<NoticeItem> // LiveData로 할 필요가 있나? 코루틴에서 돌리는데 어차피 그리고 애초에 못쓰는거 아님?
        get() = noticeArray                   // NoticeItem에 데이터 추가를 여기서 하잖아.


    suspend fun loadGeneralNoticeItems() { // 저 Coroutine쓰면서 suspend쓰나마나 결과는 같은데.. Log도 같은거 같고 뭐가 다르지???

        val url =
            "https://www.jj.ac.kr/jj/community/notice/gennotice.jsp?mode=list&board_no=1041&pager.offset=" // 학사 공지사항 홈페이지 (1페이지)
        val urlCount = 20
        val linkUrl = "https://www.jj.ac.kr/jj/community/notice/gennotice.jsp"

        Log.d("loadGeneral", "loadGeneral")

        docArray = ArrayList(emptyList())
        noticeDocArray = ArrayList(emptyList())
        notice = Elements()

        CoroutineScope(Dispatchers.IO).launch {


            try {
                // 원래 이 for문 launch로 감싸면 공지사항이 안떴는데 왜그런거지??
                for (i in 0 until 30) {
                    doc = Jsoup.connect(url + (urlCount * i).toString())
                        .get() // URL 웹사이트에 있는 html 코드를 다 끌어옴
                    docArray.add(doc) // 그 코드들을 배열에 저장함.
                    notice = docArray[i].select("#jwxe_main_content")
                        .select(".b-top-box") // notice 속성에 끌어왔던 html 코드에서 배열 순서대로 select 부분들을 끌고옴
                    noticeDocArray.add(notice) // notice에서 select로 끌고온 쿼리 부분들을 noticeDocArray에 저장함. 그니까 즉 =0에서 저부분이 noticeDocArray[0]에, =20이 noticeDocArray[1]에 들어가고 그런식.
                }
            } catch (e: IOException) {
                Log.d("NoticeFragment", "IO Error")
            }

            Log.d("notice1", notice.size.toString()) // 요걸로 체크해서 아래 배열 만듬.


            launch(Dispatchers.Default) {

                for (i in 0 until notice.size) {

                    val itemNoticeBoxes = noticeDocArray[i].select(".b-num-box").select(".b-notice-box")
                    val itemNumbers = noticeDocArray[i].select(".b-num-box")
                    val itemTitles = noticeDocArray[i].select(".b-title")
                    val itemInfos = noticeDocArray[i].select(".b-info-box")

                    if (i == 0) {
                        for (k in 0 until itemNumbers.size) {

                            val itemNumber: Element = itemNumbers[k]
                            val itemTitle: Element = itemTitles[k]
                            val itemInfo: Element = itemInfos[k]
                            val itemLink: String =
                                linkUrl + itemTitle.select("a").attr("href")

                            noticeArray.add(
                                NoticeItem(
                                    itemNumber.text(),
                                    itemTitle.text(),
                                    itemInfo.text().substring(0, 10),
                                    itemLink
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

                            noticeArray.add(
                                NoticeItem(
                                    itemNumber.text(),
                                    itemTitle.text(),
                                    itemInfo.text().substring(0, 10),
                                    itemLink
                                )
                            )
                        }
                    }
                }
            }
            Log.d("notice2", noticeArray.toString())
        }.join() // 해당 함수를 suspend로 바꿔주고 이걸 해줘야 NoticeFragment에서 binding에 lateinit 문제가 안뜨는데 지웠을 때 로그 순서보면 대충 짐작이 감.
    }


    suspend fun loadHaksaNoticeItems() {

       val url =
           "https://www.jj.ac.kr/jj/community/notice/haksanotice.jsp?mode=list&board_no=1076&pager.offset=" // 학사 공지사항 홈페이지 (1페이지)
       val urlCount = 20
       val linkUrl = "https://www.jj.ac.kr/jj/community/notice/haksanotice.jsp"
        docArray = ArrayList(emptyList())
        noticeDocArray = ArrayList(emptyList())
        notice = Elements()


        CoroutineScope(Dispatchers.IO).launch {

            try {
                // 원래 이 for문 launch로 감싸면 공지사항이 안떴는데 왜그런거지??
                for (i in 0 until 40) {
                    doc = Jsoup.connect(url + (urlCount * i).toString())
                        .get() // URL 웹사이트에 있는 html 코드를 다 끌어옴
                    docArray.add(doc) // 그 코드들을 배열에 저장함.
                    notice = docArray[i].select("#jwxe_main_content")
                        .select(".b-top-box") // notice 속성에 끌어왔던 html 코드에서 배열 순서대로 select 부분들을 끌고옴
                    noticeDocArray.add(notice) // notice에서 select로 끌고온 쿼리 부분들을 noticeDocArray에 저장함. 그니까 즉 =0에서 저부분이 noticeDocArray[0]에, =20이 noticeDocArray[1]에 들어가고 그런식.
                }
            }catch (e: IndexOutOfBoundsException) {
                Log.d("NoticeFragment", "IO Error")
            }

            Log.d("notice2", notice.size.toString()) // 요걸로 체크해서 아래 배열 만듬.

            launch(Dispatchers.Default) {

                    for (i in 0 until notice.size) {

                        val itemNoticeBoxes =
                            noticeDocArray[i].select(".b-num-box").select(".b-notice-box")
                        val itemNumbers = noticeDocArray[i].select(".b-num-box")
                        val itemTitles = noticeDocArray[i].select(".b-title")
                        val itemInfos = noticeDocArray[i].select(".b-info-box")

                        if (i == 0) {
                            for (k in 0 until itemNumbers.size) {

                                val itemNumber: Element = itemNumbers[k]
                                val itemTitle: Element = itemTitles[k]
                                val itemInfo: Element = itemInfos[k]
                                val itemLink: String =
                                    linkUrl + itemTitle.select("a").attr("href")

                                noticeArray.add(
                                    NoticeItem(
                                        itemNumber.text(),
                                        itemTitle.text(),
                                        itemInfo.text().substring(0, 10),
                                        itemLink
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

                                noticeArray.add(
                                    NoticeItem(
                                        itemNumber.text(),
                                        itemTitle.text(),
                                        itemInfo.text().substring(0, 10),
                                        itemLink
                                    )
                                )
                            }
                        }
                    }
                Log.d("notice1", noticeArray.toString())
                }

        }.join()
    }

    suspend fun loadRecruitNoticeItems() {

        val url =
            "https://www.jj.ac.kr/jj/education/recruit/recruitinfo.jsp?mode=list&board_no=1070&pager.offset=" // 학사 공지사항 홈페이지 (1페이지)
        val urlCount = 20
        val linkUrl = "https://www.jj.ac.kr/jj/education/recruit/recruitinfo.jsp"
        docArray = ArrayList(emptyList())
        noticeDocArray = ArrayList(emptyList())
        notice = Elements()

        CoroutineScope(Dispatchers.IO).launch {

            try {
                // 원래 이 for문 launch로 감싸면 공지사항이 안떴는데 왜그런거지??
                for (i in 0 until 30) {
                    doc = Jsoup.connect(url + (urlCount * i).toString())
                        .get() // URL 웹사이트에 있는 html 코드를 다 끌어옴
                    docArray.add(doc) // 그 코드들을 배열에 저장함.
                    notice = docArray[i].select("#jwxe_main_content")
                        .select(".b-top-box") // notice 속성에 끌어왔던 html 코드에서 배열 순서대로 select 부분들을 끌고옴
                    noticeDocArray.add(notice) // notice에서 select로 끌고온 쿼리 부분들을 noticeDocArray에 저장함. 그니까 즉 =0에서 저부분이 noticeDocArray[0]에, =20이 noticeDocArray[1]에 들어가고 그런식.
                }
            }catch (e: IOException) {
                Log.d("NoticeFragment", "IO Error")
            }

            Log.d("notice2", notice.size.toString()) // 요걸로 체크해서 아래 배열 만듬.

            launch(Dispatchers.Default) {

                for (i in 0 until notice.size) {

                    val itemNoticeBoxes =
                        noticeDocArray[i].select(".b-num-box").select(".b-notice-box")
                    val itemNumbers = noticeDocArray[i].select(".b-num-box")
                    val itemTitles = noticeDocArray[i].select(".b-title")
                    val itemInfos = noticeDocArray[i].select(".b-info-box")

                    if (i == 0) {
                        for (k in 0 until itemNumbers.size) {

                            val itemNumber: Element = itemNumbers[k]
                            val itemTitle: Element = itemTitles[k]
                            val itemInfo: Element = itemInfos[k]
                            val itemLink: String =
                                linkUrl + itemTitle.select("a").attr("href")

                            noticeArray.add(
                                NoticeItem(
                                    itemNumber.text(),
                                    itemTitle.text(),
                                    itemInfo.text().substring(0, 10),
                                    itemLink
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

                            noticeArray.add(
                                NoticeItem(
                                    itemNumber.text(),
                                    itemTitle.text(),
                                    itemInfo.text().substring(0, 10),
                                    itemLink
                                )
                            )
                        }
                    }
                }
                Log.d("notice1", noticeArray.toString())
            }

        }.join()
    }

    suspend fun loadJanghakNoticeItems() {

        val url =
            "https://www.jj.ac.kr/jj/community/notice/janghaknotice.jsp?mode=list&board_no=1077&pager.offset=" // 학사 공지사항 홈페이지 (1페이지)
        val urlCount = 20
        val linkUrl = "https://www.jj.ac.kr/jj/community/notice/janghaknotice.jsp"
        docArray = ArrayList(emptyList())
        noticeDocArray = ArrayList(emptyList())
        notice = Elements()

        CoroutineScope(Dispatchers.IO).launch {

            try {
                // 원래 이 for문 launch로 감싸면 공지사항이 안떴는데 왜그런거지??
                for (i in 0 until 30) {
                    doc = Jsoup.connect(url + (urlCount * i).toString())
                        .get() // URL 웹사이트에 있는 html 코드를 다 끌어옴
                    docArray.add(doc) // 그 코드들을 배열에 저장함.
                    notice = docArray[i].select("#jwxe_main_content")
                        .select(".b-top-box") // notice 속성에 끌어왔던 html 코드에서 배열 순서대로 select 부분들을 끌고옴
                    noticeDocArray.add(notice) // notice에서 select로 끌고온 쿼리 부분들을 noticeDocArray에 저장함. 그니까 즉 =0에서 저부분이 noticeDocArray[0]에, =20이 noticeDocArray[1]에 들어가고 그런식.
                }
            }catch (e: IOException) {
                Log.d("NoticeFragment", "IO Error")
            }

            Log.d("notice2", notice.size.toString()) // 요걸로 체크해서 아래 배열 만듬.

            launch(Dispatchers.Default) {

                for (i in 0 until notice.size) {

                    val itemNoticeBoxes =
                        noticeDocArray[i].select(".b-num-box").select(".b-notice-box")
                    val itemNumbers = noticeDocArray[i].select(".b-num-box")
                    val itemTitles = noticeDocArray[i].select(".b-title")
                    val itemInfos = noticeDocArray[i].select(".b-info-box")

                    if (i == 0) {
                        for (k in 0 until itemNumbers.size) {

                            val itemNumber: Element = itemNumbers[k]
                            val itemTitle: Element = itemTitles[k]
                            val itemInfo: Element = itemInfos[k]
                            val itemLink: String =
                                linkUrl + itemTitle.select("a").attr("href")

                            noticeArray.add(
                                NoticeItem(
                                    itemNumber.text(),
                                    itemTitle.text(),
                                    itemInfo.text().substring(0, 10),
                                    itemLink
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

                            noticeArray.add(
                                NoticeItem(
                                    itemNumber.text(),
                                    itemTitle.text(),
                                    itemInfo.text().substring(0, 10),
                                    itemLink
                                )
                            )
                        }
                    }
                }
                Log.d("notice1", noticeArray.toString())
            }

        }.join()
    }

    fun resetNoticeItems() {
        doc = Document("")
        docArray = ArrayList(emptyList())
        noticeDocArray = ArrayList(emptyList())
        notice = Elements()
    }



}