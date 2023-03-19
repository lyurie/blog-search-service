package org.search.feature.blog.search.usecase;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.search.assets.enums.ErrorCode;
import org.search.assets.exception.BusinessException;
import org.search.configuration.mapstuct.MapstructMapperConfig;
import org.search.feature.blog.search.usecase.domain.BlogDocuments;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class BlogSearchUseCase implements BlogSearchInputBoundary {

    private final BlogSearchOutputBoundary outputBoundary;
    private final IBlogSearchDataProvider blogSearchDataProvider;

    @Override
    public BlogSearchResponse search(BlogSearchRequest request) {
        final String query = request.getQuery();
        if(!StringUtils.hasLength(query)) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "query is required");
        }

        final BlogDocuments blogDocuments = blogSearchDataProvider.search(query, request.getSort(), request.getPage(), request.getSize());

        final BlogSearchResponse blogSearchResponse = ResponseMapper.MAPPER.toBlogSearchResponse(blogDocuments);

        return outputBoundary.getPresent(blogSearchResponse);
    }

    @Mapper(config = MapstructMapperConfig.class,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            uses = {DocumentMapper.class})
    public interface ResponseMapper {
        ResponseMapper MAPPER = org.mapstruct.factory.Mappers.getMapper(ResponseMapper.class);

//        @Mappings({
//                @Mapping(source = "end", target = "end")
//        })
        BlogSearchResponse toBlogSearchResponse(BlogDocuments blogDocuments);

    }

    @Mapper(config = MapstructMapperConfig.class, unmappedSourcePolicy = ReportingPolicy.IGNORE)
    public interface DocumentMapper {
        BlogSearchResponse.Document toBlogDocument(BlogDocuments.Document document);
    }

}
