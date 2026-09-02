package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CategoryRequest;
import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.CategoryResponse;
import com.example.tracking_order.mapper.CategoryMapper;
import com.example.tracking_order.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private ICategoryService service;
    private CategoryMapper mapper;

    @GetMapping()
    public BaseResponse<List<CategoryResponse>> getAllCategories(){
        return BaseResponse.ofSuccess(mapper.toCategoryResponseList(service.findAll()));
    }

    @PostMapping()
    public BaseResponse<CategoryResponse> create(@Valid @RequestBody CategoryRequest category){
        return BaseResponse.ofSuccess(mapper.toCategoryResponse(service.create(category)));
    }

    @PutMapping("/{id}")
    public BaseResponse<CategoryResponse> update(@PathVariable UUID id, @Valid @RequestBody CategoryRequest category){
        return BaseResponse.ofSuccess(mapper.toCategoryResponse(service.update(id, category)));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> delete(@PathVariable UUID id){
        service.delete(id);
        return BaseResponse.ofDeleteSuccess();
    }

    @GetMapping("/{id}")
    public BaseResponse<CategoryResponse> getCategory(@PathVariable UUID id){
        return BaseResponse.ofSuccess(mapper.toCategoryResponse(service.findById(id)));
    }
}
