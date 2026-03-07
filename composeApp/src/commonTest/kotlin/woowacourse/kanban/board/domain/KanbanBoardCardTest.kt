package woowacourse.kanban.board.domain

import androidx.compose.ui.test.ExperimentalTestApi
import kotlin.test.Test
import kotlin.test.assertEquals
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

    @Test
    fun `태그의 앞뒤 공백은 제거된다`() {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf(" 태그1 ", "  태그2  "),
            accountName = "테스트 계정",
        )

        assertEquals(listOf("태그1", "태그2"), cardData.tags)
    }

    @Test
    fun `공백으로만 구성된 태그는 제거된다`() {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("태그1", "   ", "", "  "),
            accountName = "테스트 계정",
        )

        assertEquals(listOf("태그1"), cardData.tags)
    }

    @Test
    fun `태그가 있으면 hasTag 리턴 값은 true이다`() {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("태그1", "   "),
            accountName = "테스트 계정",
        )

        assertTrue(cardData.hasTag())
    }

    @Test
    fun `태그가 비어 있으면 hasTag 리턴 값은 false이다`() {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("   ", ""),
            accountName = "테스트 계정",
        )

        assertFalse(cardData.hasTag())
    }
}

@OptIn(ExperimentalTestApi::class)
class KanbanBoardCardTest {}