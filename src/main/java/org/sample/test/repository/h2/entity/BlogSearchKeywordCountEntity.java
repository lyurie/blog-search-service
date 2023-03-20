package org.sample.test.repository.h2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "BLOG_SEARCH_KEYWORD_COUNT")
@Entity
public class BlogSearchKeywordCountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String searchKeyword;

    @Column(nullable = false)
    private Integer searchCount;

    @Version
    private Long version;

}
