package jp.techacademy.ayumi.sonia

import java.util.*
import java.io.Serializable

class ResponsData {
}
data class ResponseData(
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val pageInfo: Objects?,
    val items: List<Items>?
) : Serializable {

    class Items(
        val kind: String?,
        val etag: String?,
        val id: IdLists?,
        val snippet: Objects?
    )
    class IdLists(
        val kind: String?,
        val videoId: String?
    )
}