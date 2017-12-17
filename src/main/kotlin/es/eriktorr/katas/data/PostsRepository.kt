package es.eriktorr.katas.data

import es.eriktorr.katas.timeline.Post
import es.eriktorr.katas.timeline.PostsReader
import es.eriktorr.katas.timeline.PostsRecorder
import java.time.LocalDateTime

class PostsRepository : PostsRecorder, PostsReader {

    private val posts = mutableListOf<Post>()

    override fun save(post: Post) {
        posts.add(post)
    }

    override fun <T> usePosts(vararg users: String, block: (List<Post>) -> T) {
        block(posts.filter { users.contains(it.userName) }.sortedByDescending { it.timestamp })
    }

}