package com.kubilaykizilhan.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter @Setter

// BaseDto
abstract public class AuditingAwareBaseDto implements Serializable {

    // serileştirme
    public static final Long serialVersionUID = 1L;

    // ID
    protected Long id;

    // DATE Builder => default olarak ayarlıyor
    @Builder.Default
    protected Date systemDate = new Date(System.currentTimeMillis());

    //AUDITING
    //Ekleme
    @JsonIgnore //backendde giden veriyi saklar.
    protected String createdUser;
    protected Date createdDate;
    //Güncelleme
    @JsonIgnore //backendde giden veriyi saklar.
    protected String updatedUser;
    protected Date updatedDate;
} //end class