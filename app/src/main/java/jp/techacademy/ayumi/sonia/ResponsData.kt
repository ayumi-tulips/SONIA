package jp.techacademy.ayumi.sonia

import java.io.Serializable
import java.util.*

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
        val snippet: SnippetLists?
    )
    class IdLists(
        val kind: String?,
        val videoId: String?
    )
    class SnippetLists(
        val publishedAt: String?,
        val channelId: String?,
        val title: String?,
        val description: String?,
        val thumbnails:Objects?,
        val channelTitle: String?,
        val liveBroadcastContent: String?,
        val publishTime: String?
    )
}package jp.techacademy.ayumi.sonia

import java.io.Serializable
import java.util.*

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
        val snippet: SnippetLists?
    )
    class IdLists(
        val kind: String?,
        val videoId: String?
    )
    class SnippetLists(
        val publishedAt: String?,
        val channelId: String?,
        val title: String?,
        val description: String?,
        val thumbnails:Objects?,
        val channelTitle: String?,
        val liveBroadcastContent: String?,
        val publishTime: String?
    )
}