package com.an1shthomas.vendingmachine.api.impl;

import com.an1shthomas.vendingmachine.api.VendingMachine;
import com.an1shthomas.vendingmachine.entity.Bucket;
import com.an1shthomas.vendingmachine.entity.Coin;
import com.an1shthomas.vendingmachine.entity.Product;
import com.an1shthomas.vendingmachine.entity.ProductType;
import com.an1shthomas.vendingmachine.exception.CoinsNotInsertedException;
import com.an1shthomas.vendingmachine.exception.InsufficientBalanceException;
import com.an1shthomas.vendingmachine.exception.InsufficientCoinsException;
import com.an1shthomas.vendingmachine.exception.ProductNotFoundException;

import java.util.*;

public class VendingMachineImpl implements VendingMachine {
    private final Map<Product, Integer> inventory = new HashMap<>();
    private final Map<Coin, Integer> coins = new HashMap<>();

    public VendingMachineImpl() {
        initialize();
    }

    private void initialize() {
        Product COKE = new Product(ProductType.COKE, 1);
        Product SNICKER_BAR = new Product(ProductType.SNICKER_BAR, 2);
        Product CHIPS = new Product(ProductType.CHIPS, 5);
        inventory.put(COKE, 10);
        inventory.put(SNICKER_BAR, 20);
        inventory.put(CHIPS, 10);

        coins.put(Coin.ONE_DOLLAR, 20);
        coins.put(Coin.FIVE_DOLLAR, 10);
        coins.put(Coin.TEN_DOLLAR, 10);
        coins.put(Coin.TWENTY_DOLLAR, 10);
    }

    @Override
    public int getProductPrice(ProductType productType) throws ProductNotFoundException {
        Product product = inventory.entrySet().stream()
                .filter(e -> e.getKey().getType().equals(productType))
                .map(e -> e.getKey()).findFirst().orElseThrow(ProductNotFoundException::new);
        return product.getPrice();
    }

    @Override
    public Bucket getProduct(ProductType productType, Coin... coins)
            throws CoinsNotInsertedException, ProductNotFoundException,
            InsufficientCoinsException, InsufficientBalanceException {
        Product product = inventory.entrySet().stream().filter(e -> e.getKey().getType().equals(productType))
                .map(e -> e.getKey())
                .findFirst().orElseThrow(ProductNotFoundException::new);
        int insertedCoinValue = Arrays.stream(coins)
                .map(c -> c.getValue())
                .reduce(Integer::sum)
                .orElseThrow(CoinsNotInsertedException::new);
        int productPrice = product.getPrice();
        if (productPrice > insertedCoinValue) {
            throw new InsufficientCoinsException();
        }
        // Dispatch product
        inventory.put(product, inventory.get(product).intValue() - 1);
        if (inventory.get(product) <= 0) {
            inventory.remove(product);
        }
        // Dispatch balance, if necessary
        List<Coin> remainingCoins = null;
        try {
            remainingCoins = getBalance(insertedCoinValue - productPrice);
        } catch (InsufficientBalanceException e) {
            // Add the product back to inventory and re-throw the exception
            inventory.put(product, inventory.getOrDefault(product, 0) + 1);
            throw e;
        }
        return new Bucket(product, remainingCoins);
    }

    private List<Coin> getBalance(int remainingBalance) throws InsufficientBalanceException {
        List<Coin> remainingCoins = new ArrayList<>();
        while (remainingBalance > 0) {
            int initialRemaingBalance = remainingBalance;
            if (remainingBalance >= Coin.TWENTY_DOLLAR.getValue()) {
                remainingBalance = getRemaining(remainingBalance, Coin.TWENTY_DOLLAR, remainingCoins);
            } else if (remainingBalance >= Coin.TEN_DOLLAR.getValue()) {
                remainingBalance = getRemaining(remainingBalance, Coin.TEN_DOLLAR, remainingCoins);
            } else if (remainingBalance >= Coin.FIVE_DOLLAR.getValue()) {
                remainingBalance = getRemaining(remainingBalance, Coin.FIVE_DOLLAR, remainingCoins);
            } else if (remainingBalance >= Coin.ONE_DOLLAR.getValue()) {
                remainingBalance = getRemaining(remainingBalance, Coin.ONE_DOLLAR, remainingCoins);
            }
            if (initialRemaingBalance == remainingBalance) {
                // Insufficient balance.
                // Add the remainingCoins back to coins and throw an exception
                remainingCoins.stream().forEach(c -> coins.put(c, coins.getOrDefault(c, 0) + 1));
                throw new InsufficientBalanceException();
            }
        }
        return remainingCoins;
    }

    private int getRemaining(int remaining, Coin coinType, List<Coin> remainingCoins) {
        Coin coin = decrementCoins(coinType);
        if (coin != Coin.NO_COIN) {
            remainingCoins.add(coin);
            remaining = remaining - coin.getValue();
        }
        return remaining;
    }

    private Coin decrementCoins(Coin coin) {
        if (coins.get(coin) >= 0) {
            coins.put(coin, coins.get(coin) - 1);
            return coin;
        }
        return Coin.NO_COIN;
    }

}
