package es.eriktorr.katas.data

import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import es.eriktorr.katas.timeline.Post
import es.eriktorr.katas.timeline.PostPrinter
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class PostsRepositoryTest {

    companion object {
        private val ALICE = "Alice"
        private val BOB = "Bob"
        private val CHARLIE = "Charlie"

        private val MESSAGE_1 = "I love the weather today"
        private val MESSAGE_2 = "Damn! We lost!"
        private val MESSAGE_3 = "Good game though."
        private val MESSAGE_4 = "I'm in New York today! Anyone wants to have a coffee?"

        private val YEAR = 2017
        private val MONTH = 12
        private val DAY = 15
        private val HOUR = 8
        private val MINUTE = 30
        private val SECOND = 45

        private val TIMESTAMP_1 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-3)
        private val TIMESTAMP_2 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-2)
        private val TIMESTAMP_3 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-1)
        private val TIMESTAMP_4 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND)
    }

    private val postPrinter = mock<PostPrinter>()

    private val postsRepository = PostsRepository()

    @Test
    fun `save user post to time-line`() {
        postsRepository.save(Post(TIMESTAMP_1, userName = ALICE, message = MESSAGE_1))

        postsRepository.usePosts(ALICE, block = { entries -> entries.forEach { postPrinter.print(it.toString()) } })

        verify(postPrinter).print(Post(TIMESTAMP_1, userName = ALICE, message = MESSAGE_1).toString())
    }

    @Test
    fun `filter time-line by user, printing posts in reverse chronological order`() {
        postsRepository.save(Post(TIMESTAMP_1, userName = ALICE, message = MESSAGE_1))
        postsRepository.save(Post(TIMESTAMP_2, userName = BOB, message = MESSAGE_2))
        postsRepository.save(Post(TIMESTAMP_3, userName = BOB, message = MESSAGE_3))
        postsRepository.save(Post(TIMESTAMP_4, userName = CHARLIE, message = MESSAGE_4))

        postsRepository.usePosts(users = *arrayOf(BOB, CHARLIE), block = { entries -> entries.forEach { postPrinter.print(it.toString()) } })

        inOrder(postPrinter) {
            verify(postPrinter).print(Post(TIMESTAMP_4, userName = CHARLIE, message = MESSAGE_4).toString())
            verify(postPrinter).print(Post(TIMESTAMP_3, userName = BOB, message = MESSAGE_3).toString())
            verify(postPrinter).print(Post(TIMESTAMP_2, userName = BOB, message = MESSAGE_2).toString())
        }
        verifyNoMoreInteractions(postPrinter)
    }

}