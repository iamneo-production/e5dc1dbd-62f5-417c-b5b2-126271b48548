package com.hackathon.orderservice.model;

import com.hackathon.orderservice.entity.ProductItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<ProductItems> productItemsList;
}
