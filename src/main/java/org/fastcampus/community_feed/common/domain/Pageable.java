package org.fastcampus.community_feed.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pageable {
    private int pageIndex;
    private int pageSize;

    public Pageable() {
        this.pageIndex = 1;
        this.pageSize = 10;
    }

    public int getOffset() {
        return (pageIndex - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}
