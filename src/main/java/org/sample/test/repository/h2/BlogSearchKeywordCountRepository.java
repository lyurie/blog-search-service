package org.sample.test.repository.h2;

import org.sample.test.repository.h2.entity.BlogSearchKeywordCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSearchKeywordCountRepository extends JpaRepository<BlogSearchKeywordCount, Long> {

    List<BlogSearchKeywordCount> findBySearchKeyword(String SearchKeyword);
}
