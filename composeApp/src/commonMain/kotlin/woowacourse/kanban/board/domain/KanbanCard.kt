package woowacourse.kanban.board.domain

class KanbanCardData private constructor(
    val title: String,
    val content: String,
    val tags: List<String>,
    val accountName: String,
) {
    companion object {
        private const val MAX_TAG_COUNT = 5
        private const val MAX_TAG_LENGTH = 5
    }
}
