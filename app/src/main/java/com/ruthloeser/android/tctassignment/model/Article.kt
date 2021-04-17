package com.ruthloeser.android.tctassignment.model

data class Article(val id:String,
                   val metaData: ArticleMetaData,
                   val title:String,
                   val imageUrl: String,
                   val isSaved: Boolean,
                   val isLiked: Boolean,
                   val likesCount: Int,
                   val category: String,
                   val author: ArticleAuthor)

data class ArticleResult(val data:List<Article>)

data class ArticleMetaData(val creationTime:String,
                           val updateTime:String)


data class ArticleAuthor(val id:String,
                         val authorName:String,
                         val authorAvatar: ArticleAuthorAvatar)

data class ArticleAuthorAvatar(val imageUrl:String)

/*

JSON example of an article:

{
    "metaData": {
        "creationTime": "2017-04-26T12:02:58.000Z",
        "updateTime": "2017-06-19T14:27:08.000Z"
    },
    "id": "16911920427781819001",
    "title": "10 Reasons Why You Should Visit Tel Aviv",
    "imageUrl": "https://cdn.theculturetrip.com/wp-content/uploads/2017/03/jaffa-old-city-door.jpg",
    "isSaved": false,
    "isLiked": false,
    "likesCount": 12,
    "category": "Art",
    "author": {
        "id": "3571",
        "authorName": "Ben Jakob",
        "authorAvatar": {
            "imageUrl": "https://0.gravatar.com/avatar/430b63571071ec81d57f3605b59a2508?s=50&d=https://cdn.theculturetrip.com/logo/logo50.png&r=g"
        }
    }
}

*/
