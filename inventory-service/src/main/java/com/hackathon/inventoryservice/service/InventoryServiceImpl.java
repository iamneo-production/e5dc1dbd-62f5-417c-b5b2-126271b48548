package com.hackathon.inventoryservice.service;

import com.hackathon.inventoryservice.entity.Inventory;
import com.hackathon.inventoryservice.exception.InvalidProductException;
import com.hackathon.inventoryservice.model.InventoryAllocation;
import com.hackathon.inventoryservice.model.InventoryRequest;
import com.hackathon.inventoryservice.model.ProductItems;
import com.hackathon.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;
    @Override
    public  List<InventoryAllocation> quantityCheck(InventoryRequest inventoryRequest) throws InvalidProductException {
        List<InventoryAllocation> inventoryAllocationList=new ArrayList<>();
        Map<Integer, String> productStatus=new HashMap<>();
        InventoryAllocation inventoryAllocation;
        for(ProductItems productItems: inventoryRequest.getProductItemsList()){
            Inventory inventory=inventoryRepository.findById(productItems.getProductId()).orElseThrow(()-> new InvalidProductException("Invalid Product"));

            if(inventory.getProductQuantity()> productItems.getProductQuantity()){

                inventory.setProductQuantity(inventory.getProductQuantity()- productItems.getProductQuantity());
                inventoryRepository.save(inventory);

                productStatus.put(productItems.getProductId(), "Ready to Deliver");
                inventoryAllocation=new InventoryAllocation(productStatus);
            }
            else{
                productStatus.put(productItems.getProductId(), "Yet to Procure");
                inventoryAllocation=new InventoryAllocation(productStatus);
            }
            inventoryAllocationList.add(inventoryAllocation);


        }
        return inventoryAllocationList;
    }
}
