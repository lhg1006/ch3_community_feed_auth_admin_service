package org.fastcampus.community_feed.admin.ui.query;

import java.util.List;
import org.fastcampus.community_feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
