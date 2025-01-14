package org.fastcampus.community_feed.post.repository.post_queue;

import static org.fastcampus.community_feed.post.repository.entity.like.QLikeEntity.likeEntity;
import static org.fastcampus.community_feed.post.repository.entity.post.QPostEntity.postEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final UserQueueRedisRepositoryImpl queueRepository;

    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        List<PostEntity> postEntities = Optional.ofNullable(queueRepository.getPostsByUserId(userId))
            .orElse(Collections.emptyList());

        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto content = GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .commentCount(postEntity.getCommentCounter())
                .build();
            result.add(content);
        }
        return result;
    }

    private BooleanExpression buildLastDataExpression(Long lastId) {
        if (lastId == null) {
            return null;
        }
        // Ensure `postEntity` is properly initialized
        return postEntity.id.lt(lastId);
    }

    private BooleanExpression buildLikeExpression(Long userId) {
        if (userId == null) {
            return null;
        }
        // Ensure `likeEntity` is properly initialized
        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("POST"))
            .and(likeEntity.id.userId.eq(userId));
    }
}
