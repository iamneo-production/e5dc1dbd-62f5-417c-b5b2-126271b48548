package com.hackathon.orderservice.service;

import com.hackathon.orderservice.entity.Order;
import com.hackathon.orderservice.entity.Product;
import com.hackathon.orderservice.entity.ProductItems;
import com.hackathon.orderservice.exception.InvalidProductException;
import com.hackathon.orderservice.model.*;
import com.hackathon.orderservice.repository.OrderRepository;
import com.hackathon.orderservice.repository.ProductItemRepository;
import com.hackathon.orderservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) throws InvalidProductException {
        List<Products> productsList=new ArrayList<>();
        double purchaseAmount=0.0;

        String orderId=generateOrderId();

        Order order=new Order();
        order.setOrderId(orderId);
        order.setPurchaseDate(LocalDate.now());
        order.setPurchaseStatus("Payment Pending");
        orderRepository.save(order);

        for (ProductItems productItem: orderRequest.getProductItemsList()) {
            Product product=productRepository.findById(productItem.getProductId()).orElseThrow(() -> new InvalidProductException("Invalid Product Details"));
                ProductItems productItems=new ProductItems();
                productItems.setProductId(product.getProductId());
                productItems.setProductName(product.getProductName());
                productItems.setProductQuantity(productItem.getProductQuantity());
                productItems.setProductPrice(product.getProductPrice());
                productItems.setTotalPrice(product.getProductPrice()* productItem.getProductQuantity());
                productItems.setOrders(order);
                purchaseAmount+= productItem.getProductQuantity()* product.getProductPrice();
                productItemRepository.save(productItems);
                Products products=new Products(product.getProductId(),
                        product.getProductName(), productItems.getProductQuantity(),
                        productItems.getProductPrice());
                productsList.add(products);

        }

        order.setPurchaseAmount(purchaseAmount);
        orderRepository.save(order);


        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setCustomerId(1);
        orderResponse.setCustomerName("Mohan");
        orderResponse.setPurchaseDate(order.getPurchaseDate());
        orderResponse.setPurchaseAmount(order.getPurchaseAmount());
        orderResponse.setPurchaseStatus(order.getPurchaseStatus());
        orderResponse.setProductItemsList(productsList);



        return orderResponse;
    }

    @Override
    public OrderResponse findDetailsByOrderId(BillingRequest billingRequest) throws InvalidProductException {
        List<Products> productsList=new ArrayList<>();

        Order order=orderRepository.findById(billingRequest.getOrderId()).orElseThrow(()-> new InvalidProductException("Invalid Order Details"));


        List<ProductItems> productItems=productItemRepository.findAllByOrders(order);

        for (ProductItems productItem:productItems){
            Products products=new Products(productItem.getProductId(),
                    productItem.getProductName(), productItem.getProductQuantity(),
                    productItem.getProductPrice());
            productsList.add(products);
        }

        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setCustomerId(1);
        orderResponse.setCustomerName("Mohan");
        orderResponse.setPurchaseDate(order.getPurchaseDate());
        orderResponse.setPurchaseAmount(order.getPurchaseAmount());
        orderResponse.setPurchaseStatus(order.getPurchaseStatus());
        orderResponse.setProductItemsList(productsList);
        return orderResponse;
    }

    @Override
    public OrderResponse updateStatus(PurchaseStatus updatedPurchaseStatus) throws InvalidProductException {
        List<Products> productsList=new ArrayList<>();

        Order order =orderRepository.findById(updatedPurchaseStatus.getOrderId()).orElseThrow(()->new InvalidProductException("Invalid Order Details"));
        order.setPurchaseStatus(updatedPurchaseStatus.getPurchaseStatus());
        Order orderUpdatedStatus=orderRepository.save(order);
        List<ProductItems> productItems=productItemRepository.findAllByOrders(order);

        for (ProductItems productItem:productItems){
            Products products=new Products(productItem.getProductId(),
                    productItem.getProductName(), productItem.getProductQuantity(),
                    productItem.getProductPrice());
            productsList.add(products);
        }


        OrderResponse orderResponse=new OrderResponse(orderUpdatedStatus.getOrderId(),productsList,orderUpdatedStatus.getCustomerId(),
                orderUpdatedStatus.getCustomerName(),orderUpdatedStatus.getPurchaseDate(), orderUpdatedStatus.getPurchaseAmount(), orderUpdatedStatus.getPurchaseStatus());
        return orderResponse;
    }


    private String generateOrderId() {
        Random random = new Random();
        return "ORDERID" + random.nextInt(999999);
    }
}
