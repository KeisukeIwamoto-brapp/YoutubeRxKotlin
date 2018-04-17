package android.practice.com.youtuberxkotlin.network

data class apiResponse(
        val items: Array<items>,
        val nextPageToken: String
)

data class items(
        val snippet: snippet
)

data class snippet(
        val title: String,
        val thumbnails: thumbnails
)

data class thumbnails(
        val medium: image
)

data class image(
        val url: String
)