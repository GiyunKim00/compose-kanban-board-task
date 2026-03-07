package woowacourse.kanban.board.domain

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.ui.KanbanBoardCard
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
class KanbanBoardCardTest {

    @Test
    fun `모든 필드가 요구 사항대로 입력된 경우, 모든 내용이 노출된다`() = runComposeUiTest {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("태그1", "태그2", "태그3", "태그4", "태그5"),
            accountName = "테스트 계정",
        )

        setContent {
            KanbanBoardCard(kanbanCardData = cardData)
        }

        onNodeWithText("제목").assertIsDisplayed()
        onNodeWithText("내용").assertIsDisplayed()
        onNodeWithText("태그1").assertIsDisplayed()
        onNodeWithText("태그2").assertIsDisplayed()
        onNodeWithText("태그3").assertIsDisplayed()
        onNodeWithText("태그4").assertIsDisplayed()
        onNodeWithText("태그5").assertIsDisplayed()
        onNodeWithText("테스트 계정").assertIsDisplayed()
    }

    @Test
    fun `제목과 계정 정보만으로 카드를 구성할 수 있다`() = runComposeUiTest {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "",
            tags = emptyList(),
            accountName = "테스트 계정",
        )

        setContent {
            KanbanBoardCard(kanbanCardData = cardData)
        }
        onNodeWithContentDescription("Kanban Card Title").assertIsDisplayed()
        onNodeWithContentDescription("Kanban Card Content").assertDoesNotExist()
        onAllNodesWithContentDescription("Kanban Card Tag").assertCountEquals(0)
        onNodeWithContentDescription("Kanban Card Account Info").assertIsDisplayed()
    }

    @Test
    fun `내용이 없는 경우, 내용이 출력되지 않는다`() = runComposeUiTest {
        val tags = listOf("태그1", "태그2", "태그3", "태그4")
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "",
            tags = tags,
            accountName = "테스트 계정",
        )

        setContent {
            KanbanBoardCard(kanbanCardData = cardData)
        }
        onNodeWithContentDescription("Kanban Card Title").assertIsDisplayed()
        onNodeWithContentDescription("Kanban Card Content").assertDoesNotExist()
        onAllNodesWithContentDescription("Kanban Card Tag").assertCountEquals(tags.size)
        onNodeWithContentDescription("Kanban Card Account Info").assertIsDisplayed()
    }

    @Test
    fun `태그가 없는 경우, 태그가 출력되지 않는다`() = runComposeUiTest {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = emptyList(),
            accountName = "테스트 계정",
        )

        setContent {
            KanbanBoardCard(kanbanCardData = cardData)
        }

        onNodeWithContentDescription("Kanban Card Title").assertIsDisplayed()
        onNodeWithContentDescription("Kanban Card Content").assertIsDisplayed()
        onAllNodesWithContentDescription("Kanban Card Tag").assertCountEquals(0)
        onNodeWithContentDescription("Kanban Card Account Info").assertIsDisplayed()
    }

    @Test
    fun `태그가 5개를 초과하는 경우, 5개만 출력된다`() = runComposeUiTest {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("태그1", "태그2", "태그3", "태그4", "태그5", "태그6"),
            accountName = "테스트 계정",
        )

        setContent {
            KanbanBoardCard(kanbanCardData = cardData)
        }
        onNodeWithContentDescription("Kanban Card Title").assertIsDisplayed()
        onNodeWithContentDescription("Kanban Card Content").assertIsDisplayed()
        onAllNodesWithContentDescription("Kanban Card Tag").assertCountEquals(5)
        onNodeWithContentDescription("Kanban Card Account Info").assertIsDisplayed()
    }

    @Test
    fun `태그 내용이 5글자를 초과하는 경우, 5글자까지만 출력된다`() = runComposeUiTest {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("우아한테크코스", "안드로이드8기", "칸반보드리팩터링"),
            accountName = "테스트 계정",
        )

        setContent {
            KanbanBoardCard(kanbanCardData = cardData)
        }
        onNodeWithContentDescription("Kanban Card Title").assertIsDisplayed()
        onNodeWithContentDescription("Kanban Card Content").assertIsDisplayed()
        onNodeWithText("우아한테크").assertIsDisplayed()
        onNodeWithText("안드로이드").assertIsDisplayed()
        onNodeWithText("칸반보드리").assertIsDisplayed()
        onNodeWithContentDescription("Kanban Card Account Info").assertIsDisplayed()
    }
}