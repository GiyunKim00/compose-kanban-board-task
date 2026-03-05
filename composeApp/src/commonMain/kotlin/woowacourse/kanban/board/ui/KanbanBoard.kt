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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KanbanBoardCard(
    modifier: Modifier = Modifier,
    headerText: String,
    content: String,
    tags: List<String> = listOf(),
    accountName: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .background(color = Color(0xffffffff), shape = RoundedCornerShape(16.dp))
            .border(color = Color(0xffE5E7Eb), width = 1.dp, shape = RoundedCornerShape(16.dp))
            .padding(all = 17.dp),
    ) {
        CardHeader(headerText = headerText, modifier = Modifier.fillMaxWidth())
        CardContent(contentText = content, modifier = Modifier.fillMaxWidth())
        CardTags(tags = tags)
        HorizontalDivider()
        CardAccountInfo(modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(), accountName = accountName)
    }
}

@Composable
private fun CardHeader(modifier: Modifier = Modifier, headerText: String) {
    Text(
        text = headerText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.3.sp,
        lineHeight = 24.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Composable
private fun CardContent(modifier: Modifier = Modifier, contentText: String) {
    if (contentText.isNotEmpty()) {
        Text(
            text = contentText,
            fontSize = 14.sp,
            letterSpacing = 0.15.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W400,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier,
        )
    }
}

@Composable
private fun CardTags(tags: List<String> = listOf()) {
    val visible = tags.take(5).map { it.take(5) }
    if (visible.isEmpty()) return

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        visible.forEach { TagChip(text = it) }
    }
}

@Composable
private fun TagChip(modifier: Modifier = Modifier, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color(0xfff3f4f6), shape = RoundedCornerShape(16.dp))
            .padding(vertical = 5.dp, horizontal = 8.dp),
    ) {
        Text(text = text, fontWeight = FontWeight.W400, fontSize = 12.sp)
    }
}

@Composable
private fun CardAccountInfo(
    modifier: Modifier = Modifier,
    iconImage: ImageVector = Icons.Default.AccountCircle,
    accountName: String,
) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = iconImage,
            contentDescription = "프로필기본값",
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

@Composable
@Preview(showBackground = true)
fun KanbanBoard() {
    KanbanBoardCard(
        modifier = Modifier.width(286.dp),
        headerText = "Lazy Column 컴포넌트 구현",
        content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        tags = listOf("컴포넌트", "성능"),
        accountName = "다이노",
    )
}


@Composable
@Preview(showBackground = true)
fun ContentlessKanbanBoard() {
    KanbanBoardCard(
        modifier = Modifier.width(286.dp),
        headerText = "Lazy Column 컴포넌트 구현",
        content = "",
        tags = listOf("컴포넌트", "성능"),
        accountName = "다이노",
    )
}


@Composable
@Preview(showBackground = true)
fun TaglessKanbanBoard() {
    KanbanBoardCard(
        modifier = Modifier.width(286.dp),
        headerText = "Lazy Column 컴포넌트 구현",
        content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        accountName = "다이노",
    )
}


@Composable
@Preview(showBackground = true)
fun EmptyKanbanBoard() {
    KanbanBoardCard(
        modifier = Modifier.width(286.dp),
        headerText = "Lazy Column 컴포넌트 구현",
        content = "",
        accountName = "다이노",
    )
}


@Composable
@Preview(showBackground = true)
fun MaxKanbanBoard() {
    KanbanBoardCard(
        modifier = Modifier.width(286.dp),
        headerText = "너무너무 긴 제목은 한 줄까지만 노출너무너무 긴 제목은 한 줄까지만 노출",
        content = "너무너무너무 긴 설명은 두 줄까지만 노출하고 말줄임표로 처리합니다 두 줄까지만 노너무너무너무 긴 설명은 두 줄까지만 노출하고 말줄임표로 처리합니다 두 줄까지만 노",
        tags = listOf("너무너무", "긴 태그", "최대로", "5자까지진짜로", "5개제한임", "6개"),
        accountName = "너무너무너무 긴 담당자도 한 줄너무너무너무 긴 담당자도 한 줄",
    )
}