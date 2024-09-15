package org.fastcampus.community_feed.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.ui.dto.GetTableListResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.posts.GetPostTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.posts.GetPostTableResponseDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.community_feed.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.community_feed.admin.ui.query.TableQueryRepository;
import org.fastcampus.community_feed.auth.repository.entity.QUserAuthEntity;
import org.fastcampus.community_feed.post.repository.entity.post.QPostEntity;
import org.fastcampus.community_feed.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TableQueryRepositoryImpl implements TableQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;

    @Override
    public GetTableListResponseDto<GetUserTableResponseDto> getUserTable(GetUserTableRequestDto dto) {
        int total = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(
                        likeName(dto.getName())
                )
                .fetch()
                .size();

        List<Long> ids = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(
                        likeName(dto.getName())
                )
                .orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        List<GetUserTableResponseDto> result = queryFactory
                .select(
                Projections.fields(
                        GetUserTableResponseDto.class,
                        userEntity.id.as("id"),
                        userAuthEntity.email.as("email"),
                        userEntity.name.as("name"),
                        userAuthEntity.role.as("role"),
                        userEntity.regDt.as("createdAt"),
                        userEntity.updDt.as("updatedAt"),
                        userAuthEntity.lastLoginDt.as("lastLoginAt")
                    )
                )
                .from(userEntity)
                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
                .where(
                        userEntity.id.in(ids)
                )
                .orderBy(userEntity.id.desc())
                .fetch();

        return new GetTableListResponseDto<>(total, result);
    }

//    private List<GetUserTableResponseDto> getUserTable(GetUserTableRequestDto dto) {
//        return queryFactory
//                .select(
//                        Projections.fields(
//                                GetUserTableResponseDto.class,
//                                userEntity.id.as("id"),
//                                userAuthEntity.email.as("email"),
//                                userEntity.name.as("name"),
//                                userAuthEntity.role.as("role"),
//                                userEntity.regDt.as("createdAt"),
//                                userEntity.updDt.as("updatedAt"),
//                                userAuthEntity.lastLoginDt.as("lastLoginAt")
//                        )
//                )
//                .from(userEntity)
//                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
//                .where()
//                .orderBy(userEntity.id.desc())
//                .offset(dto.getOffset())
//                .limit(dto.getLimit())
//                .fetch();
//    }

    @Override
    public GetTableListResponseDto<GetPostTableResponseDto> getPostTable(
            GetPostTableRequestDto dto) {

        int total = queryFactory.select(postEntity.id)
                .from(postEntity)
                .where(
                        eqPostId(dto.getPostId())
                )
                .fetch()
                .size();

        List<Long> ids = queryFactory
                .select(postEntity.id)
                .from(postEntity)
                .where(
                        eqPostId(dto.getPostId())
                )
                .orderBy(postEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        List<GetPostTableResponseDto> result = queryFactory.select(
                        Projections.fields(
                                GetPostTableResponseDto.class,
                                postEntity.id.as("postId"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                postEntity.content.as("content"),
                                postEntity.regDt.as("createdAt"),
                                postEntity.updDt.as("updatedAt")
                        )
                )
                .from(postEntity)
                .join(userEntity).on(postEntity.author.id.eq(userEntity.id))
                .where(
                        postEntity.id.in(ids)
                )
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .orderBy(postEntity.id.desc())
                .fetch();

        return new GetTableListResponseDto<>(total, result);
    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return userEntity.name.like(name + "%");
    }

    private BooleanExpression eqPostId(Long postId) {
        return postId != null ? postEntity.id.eq(postId) : null;
    }
}
