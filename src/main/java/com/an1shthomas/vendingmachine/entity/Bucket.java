package com.an1shthomas.vendingmachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Bucket {
    private Product product;
    private List<Coin> coins;
}
