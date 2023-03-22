package org.sample.test.configuration.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.test.assets.logging.TraceLog;
import org.sample.test.assets.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingFilter implements Filter {


    private final Logger TRACE_LOGGER = LoggerFactory.getLogger("TraceLogger");

    private static final TypeReference<Object> typeRef = new TypeReference<>() {};

    private final ObjectMapper defaultObjectMapper;

    private static final String TRACE_LOG_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(cachingRequestWrapper, cachingResponseWrapper);

        final String requestBody = getRequestBody(cachingRequestWrapper);
        final String responseBody = getResponseBody(cachingResponseWrapper);
        final TraceLog traceLog = getTraceLog(cachingRequestWrapper, cachingResponseWrapper, requestBody, responseBody);
        final String logStr = defaultObjectMapper.writeValueAsString(traceLog);

        TRACE_LOGGER.info(logStr);

    }

    private TraceLog getTraceLog(HttpServletRequest request, HttpServletResponse response, String requestBody, String responseBody) throws JsonProcessingException {
        Map<String, String> requestHeaders = getRequestHeaderMap(request);

        return TraceLog.builder()
            .createdDate(DateUtil.getCurrentDateTime(TRACE_LOG_DATE_TIME_FORMAT))
            .ip(Optional.ofNullable(request.getRemoteAddr()).orElse(""))
            .uri(Optional.ofNullable(request.getRequestURI()).orElse(""))
            .method(Optional.ofNullable(request.getMethod()).orElse(""))
            .queryParams(CollectionUtils.isEmpty(request.getParameterMap()) ? Collections.emptyMap() : request.getParameterMap())
            .requestHeader(requestHeaders)
            .requestBody(StringUtils.hasLength(requestBody) ? defaultObjectMapper.readValue(requestBody, typeRef) : Collections.emptyMap())
            .status(Optional.ofNullable(response.getStatus()).orElse(0))
            .responseHeader(getResponseHeaderMap(response))
            .responseBody(StringUtils.hasLength(responseBody) ? defaultObjectMapper.readValue(responseBody, typeRef) : Collections.emptyMap())
            .build();
    }

    private Map<String, String> getRequestHeaderMap(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }

        return headers;
    }

    private Map<String, String> getResponseHeaderMap(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();

        response.getHeaderNames().forEach(name ->
            headers.put(name, response.getHeader(name))
        );

        return headers;
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);

        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                return new String(buf, 0, buf.length, StandardCharsets.UTF_8);
            }
        }

        return null;
    }

    private String getResponseBody(ContentCachingResponseWrapper response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);

        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, StandardCharsets.UTF_8);
                wrapper.copyBodyToResponse();
            }
        }

        return payload;
    }

}
