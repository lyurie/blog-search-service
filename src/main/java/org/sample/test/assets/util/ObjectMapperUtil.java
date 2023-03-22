package org.sample.test.assets.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtil {

    private static ObjectMapper defaultObjectMapper;

    @Autowired
    public void setDefaultObjectMapper(@Qualifier("defaultObjectMapper") ObjectMapper defaultObjectMapper) {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    public static ObjectMapper getDefaultObjectMapper() {
        return defaultObjectMapper;
    }

}
