package com.kubilaykizilhan.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

//LOMBOK
@Getter
@Setter

@Embeddable
public class BlogEntityEmbeddable implements Serializable {

    //Serileştirme
    public static final Long serialVersionUID=1L;


    //HEADER
    @Column(name = "Header",
            nullable = false,
            unique = true,
            insertable = true,
            updatable = true,
            length = 500,
            columnDefinition = "varchar(255) default 'blog için başlık girilmedi'")
    private String header;

    //CONTENT
    @Lob
    @Column(name = "Content", nullable = false, columnDefinition = "varchar(255) default 'content için başlık girilmedi'")
    private String content;
    // TITLE
    private String title;

    //DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemDate;

    /* Javada olsun Databasede olmasın
     @Transient
    private Object specialData;*/


}
