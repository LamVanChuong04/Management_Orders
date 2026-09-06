package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.CategoryReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.CategoryRes;
import com.example.tracking_order.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService service;


    @GetMapping()
    public ResponseEntity<BaseResponse<List<CategoryRes>>> getAllCategories(){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findAll()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<CategoryRes>> create(@Valid @RequestBody CategoryReq category){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(category)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryRes>> update(@PathVariable UUID id, @Valid @RequestBody CategoryReq category){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, category)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryRes>> getCategory(@PathVariable UUID id){
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)), HttpStatus.OK);
    }
}
