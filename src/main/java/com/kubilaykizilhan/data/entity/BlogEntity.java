package com.kubilaykizilhan.data.entity;

import com.kubilaykizilhan.audit.AuditingAwareBaseEntity;
import com.kubilaykizilhan.data.BlogEntityEmbeddable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;



@Data
@Log4j2


@Entity
@Table(name = "blogs")
// Relation Blog(N) Category(1)
public class BlogEntity extends AuditingAwareBaseEntity implements Serializable {

    //Serileştirme
    public static final Long serialVersionUID=1L;

    // Relation N(Category) 1(Blog)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id", unique = true, nullable = false)
    private Long blogId;

    // Embedded
    @Embedded
    private BlogEntityEmbeddable blogEntityEmbeddable=new BlogEntityEmbeddable();

    //RELATION
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity relationCategoryEntity;

    //Constructor Parametresiz

    public BlogEntity() {
    }

    //Constructor Parametreli

    public BlogEntity(BlogEntityEmbeddable blogEntityEmbeddable, CategoryEntity relationCategoryEntity) {
        this.blogEntityEmbeddable = blogEntityEmbeddable;
        this.relationCategoryEntity = relationCategoryEntity;
    }
}
