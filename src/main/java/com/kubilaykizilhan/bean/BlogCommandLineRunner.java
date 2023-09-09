package com.kubilaykizilhan.bean;

import com.kubilaykizilhan.business.services.IBlogServices;
import com.kubilaykizilhan.business.services.ICategoryServices;
import com.kubilaykizilhan.data.entity.BlogEntity;
import com.kubilaykizilhan.data.entity.CategoryEntity;
import com.kubilaykizilhan.data.repository.IBlogRepository;
import com.kubilaykizilhan.data.repository.ICategoryRepository;
import com.kubilaykizilhan.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// LOMBOK
@RequiredArgsConstructor

@Configuration
@Log4j2
public class BlogCommandLineRunner {

    // Injection
    private final ICategoryServices iCategoryServices;
    private final ICategoryRepository iCategoryRepository;
    private final IBlogRepository iBlogRepository;
    private final IBlogServices iBlogServices;


    // CategoryName (Save)
    public CategoryEntity categoryEntitySave(String categoryName) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName(categoryName);
        iCategoryRepository.save(categoryEntity);
        return categoryEntity;
    }

    // Random Category
    public String[] randomCategory() {
        String[] randomData = new String[5];
        randomData[0] = "bilgisayar"+ UUID.randomUUID().toString();
        randomData[1] = "laptop"+ UUID.randomUUID().toString();
        randomData[2] = "mac"+ UUID.randomUUID().toString();
        randomData[3] = "pc"+ UUID.randomUUID().toString();
        randomData[4] = "car"+ UUID.randomUUID().toString();
        // döngüde rastgele bir tane category seçecek
        for (int i = 0; i < 5; i++) {
            categoryEntitySave(randomData[i]);
        }
        // döngüde rastgele bir tane category seçecek
        for (int i = 0; i < randomData.length; i++) {
            System.out.println(randomData[i]);
        }
        return randomData;
    }

    // Blog Create
    public void blogCreate(Integer categoryNumber) {
        Iterable<CategoryEntity> iterableCategoryEntityList = iCategoryRepository.findAll();
        List<CategoryEntity> listCategoryEntityList = new ArrayList<>();
        // Iterable'dan Liste çevirdim
        iterableCategoryEntityList.forEach(listCategoryEntityList::add);

        // category varsa ekleme yapsın
        if (listCategoryEntityList != null) {
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.getBlogEntityEmbeddable().setHeader("header data");
            blogEntity.getBlogEntityEmbeddable().setTitle("title data");
            blogEntity.getBlogEntityEmbeddable().setContent("content data");
            blogEntity.setRelationCategoryEntity(listCategoryEntityList.get(categoryNumber));
            iBlogRepository.save(blogEntity);
        } else {
            throw new ResourceNotFoundException("Category Listesi yoktur");
        }
    }

    @Bean
    public CommandLineRunner blogCommandLineRunnerMethod() {
        return args -> {
            // category yoksa yeni kategoriler eklensin.
            if(randomCategory()==null){
                randomCategory();
            }

            //blogCreate(0);

            //System.out.println("CommandLineRunner Çalıştı");
            //log.info("CommandLineRunner Çalıştı");
        };
    }
}