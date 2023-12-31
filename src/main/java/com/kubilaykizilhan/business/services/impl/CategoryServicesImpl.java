package com.kubilaykizilhan.business.services.impl;

import com.kubilaykizilhan.bean.ModelMapperBean;
import com.kubilaykizilhan.business.dto.CategoryDto;
import com.kubilaykizilhan.business.services.ICategoryServices;
import com.kubilaykizilhan.data.entity.CategoryEntity;
import com.kubilaykizilhan.data.repository.ICategoryRepository;
import com.kubilaykizilhan.exception.KubilayKizilhanException;
import com.kubilaykizilhan.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICES
@Service
public class CategoryServicesImpl implements ICategoryServices<CategoryDto, CategoryEntity> {

    // Injection (Field) => 1.YOL
    /*
    @Autowired
    private ICategoryRepository iCategoryRepository;
    */

    // Injection (Constructor Field) => 2.YOL
    /*
    private final ICategoryRepository iCategoryRepository;
    @Autowired
    public CategoryServicesImpl(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }
    */

    // Injection (Lombok Constructor Field) => 3.YOL
    private final ICategoryRepository iCategoryRepository;
    private final ModelMapperBean modelMapperBean;


    // MODEL MAPPER
    @Override
    public CategoryDto entityToDto(CategoryEntity categoryEntity) {
        return modelMapperBean.modelMapperMethod().map(categoryEntity,CategoryDto.class);
    }

    @Override
    public CategoryEntity dtoToEntity(CategoryDto categoryDto) {
        return  modelMapperBean.modelMapperMethod().map(categoryDto,CategoryEntity.class);
    }

    // CREATE
    @Override
    @Transactional // create, delete, update
    public CategoryDto categoryServiceCreate(CategoryDto categoryDto) {
        if(categoryDto!=null){
            CategoryEntity categoryEntity=dtoToEntity(categoryDto);
            iCategoryRepository.save(categoryEntity);
            categoryDto.setId(categoryEntity.getCategoryId());
            categoryDto.setSystemDate(categoryEntity.getSystemDate());
        }else{
            throw  new NullPointerException( " CategoryDto null veri");
        }
        return categoryDto;
    }

    // LIST
    @Override
    public List<CategoryDto> categoryServiceList() {
        Iterable<CategoryEntity> entityIterable=  iCategoryRepository.findAll();
        // Dto To entityb List
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        for (CategoryEntity entity:  entityIterable) {
            CategoryDto categoryDto=entityToDto(entity);
            categoryDtoList.add(categoryDto);
        }
        log.info("Liste Sayısı: "+categoryDtoList.size());
        return categoryDtoList;
    }

    // FIND
    @Override
    public CategoryDto categoryServiceFindById(Long id) {
        // 1.YOL (FIND)
        /*
        Optional<CategoryEntity> findOptionalCategoryEntity=  iCategoryRepository.findById(id);
        CategoryDto categoryDto=entityToDto(findOptionalCategoryEntity.get());
        if(findOptionalCategoryEntity.isPresent()){
            return categoryDto;
        }
        */

        // 2.YOL (FIND)
        CategoryEntity findCategoryEntity=  null;
        if(id!=null){
            findCategoryEntity=  iCategoryRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException(id+" nolu id yoktur"));
        }else if(id==null) {
            throw new KubilayKizilhanException("İd null olarak geldi");
        }
        return entityToDto(findCategoryEntity);
    }

    // UPDATE
    @Override
    @Transactional // create, delete, update
    public CategoryDto categoryServiceUpdate(Long id, CategoryDto categoryDto) {
        // Önce Bul
        CategoryDto categoryFindDto= categoryServiceFindById(id);
        if(categoryFindDto!=null){
            CategoryEntity categoryEntity=dtoToEntity(categoryFindDto);
            categoryEntity.setCategoryName(categoryDto.getCategoryName());
            iCategoryRepository.save(categoryEntity);
            // Dönüştede ID ve Date Set et
        }
        return categoryDto;
    }

    // DELETE
    @Override
    @Transactional // create, delete, update
    public CategoryDto categoryServiceDeleteById(Long id) {
        // Önce Bul
        CategoryDto categoryFindDto= categoryServiceFindById(id);
        if(categoryFindDto!=null){
            iCategoryRepository.deleteById(id);
            // Dönüştede ID ve Date Set et
        }
        return categoryFindDto;
    }

} //end class