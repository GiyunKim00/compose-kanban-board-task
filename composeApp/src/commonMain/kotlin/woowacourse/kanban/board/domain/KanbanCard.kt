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

        fun create(
            title: String,
            content: String,
            tags: List<String>,
            accountName: String,
        ): KanbanCardData {
            require(title.isNotBlank()) { "[KanbanCard] 제목은 필수 입력 항목입니다." }
        }
    }

    fun hasContent(): Boolean = content.isNotBlank()
    fun hasTag(): Boolean = tags.isNotEmpty()
}
