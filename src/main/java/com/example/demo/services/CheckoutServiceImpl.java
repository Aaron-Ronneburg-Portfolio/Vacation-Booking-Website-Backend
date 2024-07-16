package com.example.demo.services;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Cart;
import com.example.demo.dao.CartRepository;

import java.util.Set;
import java.util.UUID;



@Service
public class CheckoutServiceImpl implements CheckoutService{


    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl( CartRepository cartRepository) {

        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems != null) {
            cartItems.forEach(item -> cart.add(item));
        }

        cart.setCartItem(purchase.getCartItems());
        cart.setCustomer(purchase.getCustomer());

        cart.setStatus(StatusType.ordered);



        Customer customer = purchase.getCustomer();
        customer.add(cart);
        cartRepository.save(cart);


        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
       return UUID.randomUUID().toString();
    }
}
