package com.hackathon.inventoryservice.service;

import com.hackathon.inventoryservice.exception.InvalidProductException;
import com.hackathon.inventoryservice.model.InventoryAllocation;
import com.hackathon.inventoryservice.model.InventoryRequest;

import java.util.List;

public interface InventoryService {
    List<InventoryAllocation> quantityCheck(InventoryRequest inventoryRequest) throws InvalidProductException;
}
