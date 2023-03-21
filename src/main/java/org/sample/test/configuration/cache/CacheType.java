package org.sample.test.configuration.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {

    TOP_SEARCH_KEYWORDS("topSearchKeywords", 10, 10);

    private final String cacheName;
    private final int expiredAfterWriteSec;
    private final int maximumSize;

}
