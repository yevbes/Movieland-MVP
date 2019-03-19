package com.yevbes.movieland.presentation.top_rated.model.res


import com.google.gson.annotations.SerializedName

data class TopRatedMoviesRes(
    @SerializedName("average_rating")
    val averageRating: Double,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("comments")
    val comments: Comments,
    @SerializedName("created_by")
    val createdBy: CreatedBy,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("object_ids")
    val objectIds: ObjectIds,
    @SerializedName("page")
    val page: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("public")
    val `public`: Boolean,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("revenue")
    val revenue: Long,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("sort_by")
    val sortBy: String,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
){

data class Comments(
    @SerializedName("movie:100402")
    val movie100402: Any,
    @SerializedName("movie:10138")
    val movie10138: Any,
    @SerializedName("movie:10195")
    val movie10195: Any,
    @SerializedName("movie:102382")
    val movie102382: Any,
    @SerializedName("movie:102899")
    val movie102899: Any,
    @SerializedName("movie:10658")
    val movie10658: Any,
    @SerializedName("movie:118340")
    val movie118340: Any,
    @SerializedName("movie:1250")
    val movie1250: Any,
    @SerializedName("movie:127585")
    val movie127585: Any,
    @SerializedName("movie:13056")
    val movie13056: Any,
    @SerializedName("movie:13995")
    val movie13995: Any,
    @SerializedName("movie:166424")
    val movie166424: Any,
    @SerializedName("movie:1724")
    val movie1724: Any,
    @SerializedName("movie:1726")
    val movie1726: Any,
    @SerializedName("movie:1771")
    val movie1771: Any,
    @SerializedName("movie:1927")
    val movie1927: Any,
    @SerializedName("movie:1930")
    val movie1930: Any,
    @SerializedName("movie:1979")
    val movie1979: Any,
    @SerializedName("movie:2080")
    val movie2080: Any,
    @SerializedName("movie:22059")
    val movie22059: Any,
    @SerializedName("movie:24428")
    val movie24428: Any,
    @SerializedName("movie:246655")
    val movie246655: Any,
    @SerializedName("movie:263115")
    val movie263115: Any,
    @SerializedName("movie:271110")
    val movie271110: Any,
    @SerializedName("movie:283995")
    val movie283995: Any,
    @SerializedName("movie:284052")
    val movie284052: Any,
    @SerializedName("movie:284053")
    val movie284053: Any,
    @SerializedName("movie:293660")
    val movie293660: Any,
    @SerializedName("movie:315635")
    val movie315635: Any,
    @SerializedName("movie:36586")
    val movie36586: Any,
    @SerializedName("movie:36647")
    val movie36647: Any,
    @SerializedName("movie:36648")
    val movie36648: Any,
    @SerializedName("movie:36657")
    val movie36657: Any,
    @SerializedName("movie:36658")
    val movie36658: Any,
    @SerializedName("movie:36668")
    val movie36668: Any,
    @SerializedName("movie:49538")
    val movie49538: Any,
    @SerializedName("movie:557")
    val movie557: Any,
    @SerializedName("movie:558")
    val movie558: Any,
    @SerializedName("movie:559")
    val movie559: Any,
    @SerializedName("movie:68721")
    val movie68721: Any,
    @SerializedName("movie:71676")
    val movie71676: Any,
    @SerializedName("movie:7220")
    val movie7220: Any,
    @SerializedName("movie:76170")
    val movie76170: Any,
    @SerializedName("movie:76338")
    val movie76338: Any,
    @SerializedName("movie:8867")
    val movie8867: Any,
    @SerializedName("movie:9480")
    val movie9480: Any,
    @SerializedName("movie:9738")
    val movie9738: Any,
    @SerializedName("movie:9947")
    val movie9947: Any,
    @SerializedName("movie:99861")
    val movie99861: Any
)

data class ObjectIds(
    @SerializedName("movie:100402")
    val movie100402: String,
    @SerializedName("movie:10138")
    val movie10138: String,
    @SerializedName("movie:10195")
    val movie10195: String,
    @SerializedName("movie:102382")
    val movie102382: String,
    @SerializedName("movie:102899")
    val movie102899: String,
    @SerializedName("movie:10658")
    val movie10658: String,
    @SerializedName("movie:118340")
    val movie118340: String,
    @SerializedName("movie:1250")
    val movie1250: String,
    @SerializedName("movie:127585")
    val movie127585: String,
    @SerializedName("movie:13056")
    val movie13056: String,
    @SerializedName("movie:13995")
    val movie13995: String,
    @SerializedName("movie:166424")
    val movie166424: String,
    @SerializedName("movie:1724")
    val movie1724: String,
    @SerializedName("movie:1726")
    val movie1726: String,
    @SerializedName("movie:1771")
    val movie1771: String,
    @SerializedName("movie:1927")
    val movie1927: String,
    @SerializedName("movie:1930")
    val movie1930: String,
    @SerializedName("movie:1979")
    val movie1979: String,
    @SerializedName("movie:2080")
    val movie2080: String,
    @SerializedName("movie:22059")
    val movie22059: String,
    @SerializedName("movie:24428")
    val movie24428: String,
    @SerializedName("movie:246655")
    val movie246655: String,
    @SerializedName("movie:263115")
    val movie263115: String,
    @SerializedName("movie:271110")
    val movie271110: String,
    @SerializedName("movie:283995")
    val movie283995: String,
    @SerializedName("movie:284052")
    val movie284052: String,
    @SerializedName("movie:284053")
    val movie284053: String,
    @SerializedName("movie:293660")
    val movie293660: String,
    @SerializedName("movie:315635")
    val movie315635: String,
    @SerializedName("movie:36586")
    val movie36586: String,
    @SerializedName("movie:36647")
    val movie36647: String,
    @SerializedName("movie:36648")
    val movie36648: String,
    @SerializedName("movie:36657")
    val movie36657: String,
    @SerializedName("movie:36658")
    val movie36658: String,
    @SerializedName("movie:36668")
    val movie36668: String,
    @SerializedName("movie:49538")
    val movie49538: String,
    @SerializedName("movie:557")
    val movie557: String,
    @SerializedName("movie:558")
    val movie558: String,
    @SerializedName("movie:559")
    val movie559: String,
    @SerializedName("movie:68721")
    val movie68721: String,
    @SerializedName("movie:71676")
    val movie71676: String,
    @SerializedName("movie:7220")
    val movie7220: String,
    @SerializedName("movie:76170")
    val movie76170: String,
    @SerializedName("movie:76338")
    val movie76338: String,
    @SerializedName("movie:8867")
    val movie8867: String,
    @SerializedName("movie:9480")
    val movie9480: String,
    @SerializedName("movie:9738")
    val movie9738: String,
    @SerializedName("movie:9947")
    val movie9947: String,
    @SerializedName("movie:99861")
    val movie99861: String
)

data class CreatedBy(
    @SerializedName("gravatar_hash")
    val gravatarHash: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
)

data class Result(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)}