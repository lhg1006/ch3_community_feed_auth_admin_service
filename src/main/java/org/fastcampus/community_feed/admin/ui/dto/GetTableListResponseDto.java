package org.fastcampus.community_feed.admin.ui.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTableListResponseDto<T> {
    private int totalCount;
    List<T> tableList;
}
