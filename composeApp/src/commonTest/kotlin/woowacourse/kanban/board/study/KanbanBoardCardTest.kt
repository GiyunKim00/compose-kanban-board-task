package woowacourse.kanban.board.study

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.domain.KanbanCardData
import woowacourse.kanban.board.ui.KanbanBoardCard
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class KanbanCardDataTest {

}

@OptIn(ExperimentalTestApi::class)
class KanbanBoardCardTest {

    @Test
    fun `모든 필드가 요구 사항대로 입력된 경우, 모든 내용이 노출된다`() = runComposeUiTest {
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "내용",
            tags = listOf("태그1", "태그2", "태그3", "태그4", "태그5"),
            accountName = "테스트 계정"
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
    fun `내용이 없는 경우 출력되지 않는다`() = runComposeUiTest {
        val tags = listOf("태그1", "태그2", "태그3", "태그4")
        val cardData = KanbanCardData.create(
            title = "제목",
            content = "",
            tags = tags,
            accountName = "테스트 계정"
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
            accountName = "테스트 계정"
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
    fun ``() = runComposeUiTest {

    }
}