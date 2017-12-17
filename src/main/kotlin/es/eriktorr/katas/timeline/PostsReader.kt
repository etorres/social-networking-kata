package es.eriktorr.katas.timeline

interface PostsReader {

    fun <T> usePosts(vararg users: String, block: (List<Post>) -> T)

}