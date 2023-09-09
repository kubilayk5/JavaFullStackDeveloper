package com.kubilaykizilhan.business.dto;

import com.kubilaykizilhan.annotation.UniqueCategoryName;
import com.kubilaykizilhan.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder

public class CategoryDto extends AuditingAwareBaseDto implements Serializable {

    //Serileştirme
    public static final Long serialVersionUID=1L;

    //CategoryName
    @UniqueCategoryName // kendi annotationımı kendim yazdım.
    @NotEmpty(message = "{blog.category.validation.constraints.NotNull.message}")
    @Size(min=2, message = "{blog.category.least.validation.constraints.NotNull.message}")
    private String categoryName;
}
