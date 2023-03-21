package org.sample.test.configuration.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@EnableCaching
@Configuration
public class CaffeineCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        List<CaffeineCache> caffeineCaches = Arrays.stream(CacheType.values())
            .map(cacheType ->
                new CaffeineCache(
                    cacheType.getCacheName(),
                    Caffeine.newBuilder()
                        .recordStats()
                        .expireAfterWrite(Duration.ofSeconds(cacheType.getExpiredAfterWriteSec()))
                        .maximumSize(cacheType.getMaximumSize())
                        .build())
            ).toList();

        simpleCacheManager.setCaches(caffeineCaches);

        return simpleCacheManager;
    }

}
