package org.sample.test.repository.h2;

import org.sample.test.repository.h2.entity.BlogSearchKeywordCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface BlogSearchKeywordCountRepository extends JpaRepository<BlogSearchKeywordCountEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    BlogSearchKeywordCountEntity findBySearchKeyword(String searchKeyword);

}
