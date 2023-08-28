package com.hackathon.inventoryservice.controller;

import com.hackathon.inventoryservice.exception.InvalidProductException;
import com.hackathon.inventoryservice.model.ApiResponse;
import com.hackathon.inventoryservice.model.InventoryAllocation;
import com.hackathon.inventoryservice.model.InventoryRequest;
import com.hackathon.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static final String STATUS="SUCCESS";
    @Autowired
    private InventoryService inventoryService;

    @PutMapping("/quantity-check")
    public ResponseEntity<ApiResponse<List<InventoryAllocation>>> quantityCheck(
            @RequestBody InventoryRequest inventoryRequest) throws InvalidProductException {

        List<InventoryAllocation> inventoryResponse=inventoryService.quantityCheck(inventoryRequest);

        ApiResponse<List<InventoryAllocation>> response=new ApiResponse<>(STATUS,inventoryResponse,null);
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }
}
