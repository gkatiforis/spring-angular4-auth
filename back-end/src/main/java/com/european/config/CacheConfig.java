package com.european.config;

import com.european.model.User;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Component;

/**
 * Config CacheManager
 */
@Component
public class CacheConfig {

    private CacheManager cacheManager;
    private Cache<String, User> userCache;

    public CacheConfig() {
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().build();
        cacheManager.init();

        userCache = cacheManager
                .createCache("user", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(
                                String.class, User.class,
                                ResourcePoolsBuilder.heap(10)));
    }
    public Cache<String, User> getUserCache() {
        return cacheManager.getCache("user", String.class, User.class);
    }
}