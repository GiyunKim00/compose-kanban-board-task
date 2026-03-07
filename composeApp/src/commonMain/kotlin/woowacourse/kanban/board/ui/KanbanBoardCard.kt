package woowacourse.kanban.board.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.domain.KanbanCardData

/**
 * KanbanBoardCard UI입니다. Header, Content, Tags, AccountInfo로 구성되어 있습니다.
 * @param modifier Modifier
 * @param kanbanCardData KanbanCard의 데이터입니다.
 */
@Composable
fun KanbanBoardCard(
    modifier: Modifier = Modifier,
    kanbanCardData: KanbanCardData,
) {
    Column(
        modifier = modifier
            .background(color = Color(0xffffffff), shape = RoundedCornerShape(16.dp))
            .border(color = Color(0xffE5E7Eb), width = 1.dp, shape = RoundedCornerShape(16.dp))
            .padding(all = 17.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        CardTitle(
            modifier = Modifier.fillMaxWidth(),
            title = kanbanCardData.title,
        )

        if (kanbanCardData.hasContent()) {
            CardContent(
                modifier = Modifier.fillMaxWidth(),
                content = kanbanCardData.content,
            )
        }

        if (kanbanCardData.hasTag()) CardTagsSection(tags = kanbanCardData.tags)

        HorizontalDivider()

        CardAccountInfo(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            accountImage = Icons.Default.AccountCircle, /* 추후 api나, Async 등으로 이미지를 불러올 경우 수정할 예정. */
            accountName = kanbanCardData.accountName,
        )
    }
}

/**
 * 최대 1줄까지 표시되는 Card의 Header입니다.
 * @param modifier Modifier
 * @param title 카드 제목으로, 너무 길면...로 표시됩니다.
 */
@Composable
private fun CardTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.3.sp,
        lineHeight = 24.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

/**
 * 최대 2줄까지 표시되는 Card의 Content입니다.
 * @param modifier Modifier
 * @param content 카드 본문으로, 너무 길면 ...로 표시됩니다.
 */
@Composable
private fun CardContent(modifier: Modifier = Modifier, content: String) {
    Text(
        text = content,
        fontSize = 14.sp,
        letterSpacing = 0.15.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

/**
 * CardTag 섹션입니다. TagChip이 표시됩니다.
 * @param tags 카드 태그로, 최대 5개까지 입력할 수 있습니다.
 */
@Composable
private fun CardTagsSection(tags: List<String> = listOf()) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tags.forEach { TagChip(chipContent = it) }
    }
}

/**
 * CardTag의 Chip입니다.
 * @param modifier Modifier
 * @param chipContent TagChip의 내용입니다.
 */
@Composable
private fun TagChip(modifier: Modifier = Modifier, chipContent: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color(0xfff3f4f6), shape = RoundedCornerShape(16.dp))
            .padding(vertical = 5.dp, horizontal = 8.dp),
    ) {
        Text(text = chipContent, fontWeight = FontWeight.W400, fontSize = 12.sp)
    }
}

/**
 * CardAccountInfo 섹션입니다.
 * @param modifier Modifier
 * @param accountImage 프로필 아이콘입니다. 기본 값은 Icons.Default.AccountCircle입니다.
 * @param accountName 카드 계정 이름으로, 너무 길면 ...로 표시됩니다.
 */
@Composable
private fun CardAccountInfo(
    modifier: Modifier = Modifier,
    accountImage: ImageVector = Icons.Default.AccountCircle,
    accountName: String,
) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = accountImage,
            contentDescription = "프로필 아이콘",
            modifier = Modifier.size(24.dp),
            tint = Color(0xff838383),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = accountName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}