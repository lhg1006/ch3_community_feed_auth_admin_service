package org.fastcampus.community_feed.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.community_feed.common.domain.Pageable;
import org.junit.jupiter.api.Test;

class PageableTest {

    @Test
    void givenPageable_whenGetOffset_thenShouldReturnOffset() {
        // Given
        Pageable pageable = new Pageable(1, 10);

        // When
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // Then
        assertEquals(0, offset);
        assertEquals(10, limit);
    }

    @Test
    void givenPageable_whenGetOffset_thenShouldReturnOffset2() {
        // Given
        Pageable pageable = new Pageable(2, 10);

        // When
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // Then
        assertEquals(10, offset);
        assertEquals(10, limit);
    }

}
