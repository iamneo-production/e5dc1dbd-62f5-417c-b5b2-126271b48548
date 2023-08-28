package com.hackathon.paymentservice.feign;

import com.hackathon.paymentservice.model.ApiResponse;
import com.hackathon.paymentservice.model.InventoryAllocation;
import com.hackathon.paymentservice.model.InventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {

    @PutMapping("/quantity-check")
    public ResponseEntity<ApiResponse<List<InventoryAllocation>>> quantityCheck(@RequestBody InventoryRequest inventoryRequest);
}
