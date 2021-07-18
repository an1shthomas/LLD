package com.an1shthomas.vendingmachine.api;

import com.an1shthomas.vendingmachine.entity.Bucket;
import com.an1shthomas.vendingmachine.entity.Coin;
import com.an1shthomas.vendingmachine.entity.ProductType;
import com.an1shthomas.vendingmachine.exception.CoinsNotInsertedException;
import com.an1shthomas.vendingmachine.exception.InsufficientBalanceException;
import com.an1shthomas.vendingmachine.exception.InsufficientCoinsException;
import com.an1shthomas.vendingmachine.exception.ProductNotFoundException;

public interface VendingMachine {
    int getProductPrice(ProductType product) throws ProductNotFoundException;

    Bucket getProduct(ProductType product, Coin... coins) throws CoinsNotInsertedException, ProductNotFoundException, InsufficientCoinsException, InsufficientBalanceException;

}
