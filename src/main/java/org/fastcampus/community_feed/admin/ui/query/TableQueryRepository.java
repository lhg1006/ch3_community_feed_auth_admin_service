package org.fastcampus.community_feed.admin.ui.query;

import org.fastcampus.community_feed.admin.ui.dto.GetTableListResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.posts.GetPostTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.posts.GetPostTableResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableResponseDto;

public interface TableQueryRepository {
    GetTableListResponseDto<GetUserTableResponseDto> getUserTable(GetUserTableRequestDto dto);
    GetTableListResponseDto<GetPostTableResponseDto> getPostTable(GetPostTableRequestDto dto);
}
