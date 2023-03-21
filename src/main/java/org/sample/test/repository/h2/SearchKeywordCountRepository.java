package org.sample.test.repository.h2;

import org.sample.test.repository.h2.entity.SearchKeywordCountEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface SearchKeywordCountRepository extends JpaRepository<SearchKeywordCountEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    SearchKeywordCountEntity findBySearchKeyword(String searchKeyword);

    List<SearchKeywordCountEntity> findBySearchCountGreaterThan(int searchCount, Pageable pageable);

}
