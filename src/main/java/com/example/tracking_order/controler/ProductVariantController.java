package com.example.tracking_order.controler;

import com.example.tracking_order.dto.request.ProductVariantReq;
import com.example.tracking_order.common.BaseResponse;
import com.example.tracking_order.dto.response.ProductVariantRes;
import com.example.tracking_order.service.IProductVariantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-variants")
@AllArgsConstructor
@Tag(name = "Product variant Controller")
public class ProductVariantController {
    private final IProductVariantService service;

    @Operation(method = "POST", summary = "Add new product variant", description = "Send a request via this API to add new product variant")
    @PostMapping()
    public ResponseEntity<BaseResponse<ProductVariantRes>> create(@Valid @RequestBody ProductVariantReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.create(req)),  HttpStatus.CREATED);
    }


    @Operation(method = "POST", summary = "Add images product variant", description = "Send a request via this API to add images product variant")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile[] files) {
        List<String> uploadedFiles = new ArrayList<>();
        for(MultipartFile file : files) {
            try{
                UUID fileName = UUID.randomUUID();
                Path path = Paths.get("images-upload/" + fileName);
                Files.write(path, file.getBytes());
                uploadedFiles.add("Upload thành công: " + file.getOriginalFilename());
            }catch (Exception e){
                uploadedFiles.add("Upload thất bại: " + file.getOriginalFilename() + " - " + e.getMessage());
            }
        }
        return ResponseEntity.ok(uploadedFiles);
    }

    @Operation(method = "POST", summary = "Update product variant", description = "Send a request via this API to update product variant")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductVariantRes>> update(@PathVariable("id") UUID id,
                                                  @Valid @RequestBody ProductVariantReq req) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.update(id, req)),   HttpStatus.OK);
    }
    @Operation(method = "DELETE", summary = "Delete product variant", description = "Send a request via this API to delete product variant")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new ResponseEntity<>(BaseResponse.ofDeleteSuccess(), HttpStatus.OK);
    }
    @Operation(method = "GET", summary = "Get product variant", description = "Send a request via this API to get product variant")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductVariantRes>> get(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BaseResponse.ofSuccess(service.findById(id)), HttpStatus.OK);
    }
}
