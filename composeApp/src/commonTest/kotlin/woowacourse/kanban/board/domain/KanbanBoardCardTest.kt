package woowacourse.kanban.board.domain

import androidx.compose.ui.test.ExperimentalTestApi
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KanbanCardDataTest {
    @Test
    fun `제목이 공백만 있으면 생성할 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            KanbanCardData.create(
                title = "   ",
                content = "내용",
                tags = listOf("태그1"),
                accountName = "테스트 계정",
            )
        }
    }

    @Test
    fun `내용이 있으면 hasContent 리턴 값은 true이다`() {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = emptyList(),
            accountName = "테스트 계정",
        )

        assertTrue(cardData.hasContent())
    }

    @Test
    fun `내용이 공백이면 hasContent 리턴 값은 false이다`() {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "   ",
            tags = emptyList(),
            accountName = "테스트 계정",
        )

        assertFalse(cardData.hasContent())
    }
}

@OptIn(ExperimentalTestApi::class)
class KanbanBoardCardTest {}