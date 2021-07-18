package com.an1shthomas.vendingmachine;

import com.an1shthomas.vendingmachine.api.VendingMachine;
import com.an1shthomas.vendingmachine.api.impl.VendingMachineImpl;
import com.an1shthomas.vendingmachine.entity.Bucket;
import com.an1shthomas.vendingmachine.entity.Coin;
import com.an1shthomas.vendingmachine.entity.ProductType;
import com.an1shthomas.vendingmachine.exception.CoinsNotInsertedException;
import com.an1shthomas.vendingmachine.exception.InsufficientBalanceException;
import com.an1shthomas.vendingmachine.exception.InsufficientCoinsException;
import com.an1shthomas.vendingmachine.exception.ProductNotFoundException;

public class Demo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachineImpl();
        try {
            System.out.println(vendingMachine.getProductPrice(ProductType.SNICKER_BAR));
            Bucket bucket = vendingMachine.getProduct(ProductType.SNICKER_BAR, Coin.TWENTY_DOLLAR);
            System.out.println("Bucket: " + bucket);
        } catch (ProductNotFoundException | CoinsNotInsertedException | InsufficientCoinsException | InsufficientBalanceException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
