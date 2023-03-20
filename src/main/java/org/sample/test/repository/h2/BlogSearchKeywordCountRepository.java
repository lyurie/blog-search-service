package org.sample.test.repository.h2;

import org.sample.test.repository.h2.entity.BlogSearchKeywordCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSearchKeywordCountRepository extends JpaRepository<BlogSearchKeywordCountEntity, Long> {

    BlogSearchKeywordCountEntity findBySearchKeyword(String searchKeyword);

}
