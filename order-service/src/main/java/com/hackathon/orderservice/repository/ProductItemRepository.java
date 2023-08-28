package com.hackathon.orderservice.repository;

import com.hackathon.orderservice.entity.Order;
import com.hackathon.orderservice.entity.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItems, Integer> {


    List<ProductItems> findAllByOrders(Order order);
}
