package woowacourse.kanban.board.domain

/**
 * KanbanBoardCard 도메인 모델입니다.
 * 카드 생성 규칙을 적용합니다.
 * 생성은 [create] 팩토리 메서드로 수행합니다.
 */
class KanbanCardData private constructor(
    val title: String,
    val content: String,
    val tags: List<String>,
    val accountName: String,
) {
    companion object {
        private const val MAX_TAG_COUNT = 5
        private const val MAX_TAG_LENGTH = 5

        /**
         * [KanbanCardData] 객체 생성 팩토리 메서드입니다.
         * @param title 필수 | 제목
         * @param content 본문
         * @param tags 태그
         * @param accountName 필수 | 계정명
         * @throws IllegalArgumentException 제목이나 계정명이 공백일 경우 예외를 던집니다.
         */
        fun create(
            title: String,
            content: String,
            tags: List<String>,
            accountName: String,
        ): KanbanCardData {
            require(title.isNotBlank()) { "[KanbanCard] 제목은 필수 입력 항목입니다." }
            require(accountName.isNotBlank()) { "[KanbanCard] 계정명은 필수 입력 항목입니다." }

            return KanbanCardData(
                title = title,
                content = content,
                tags = tags
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .take(MAX_TAG_COUNT)
                    .map { it.take(MAX_TAG_LENGTH) },
                accountName = accountName,
            )
        }
    }

    /**
     * 카드 내용 존재 여부를 리턴합니다.
     * @return 내용이 공백이 아니면 true 리턴.
     */
    fun hasContent(): Boolean = content.isNotBlank()
    /**
     * 태그 존재 여부를 리턴합니다
     * @return 태그가 있다면 true 리턴.
     */
    fun hasTag(): Boolean = tags.isNotEmpty()
}
