package woowacourse.kanban.board.domain

/**
 * KanbanBoardCard 도메인 모델입니다.
 *
 * 카드의 제목, 내용, 태그, 계정 정보를 포함하며, 카드 생성 시 하기 규칙을 적용합니다.
 * - 제목은 공백이거나 비어 있을 수 없습니다.
 * - 계정명은 공백이거나 비어 있을 수 없습니다.
 * - 태그는 공백, 빈 태그는 제거하며, 최대 5개까지만 허용합니다.
 * - 각 태그는 최대 5자까지 출력되며, 그 이상은 출력하지 않습니다.
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
