package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class Sulfuras implements IsSulfuras {

    private Item item;

    public Sulfuras(Item item) {
        this.item = item;
    }

    public void decreaseQuality() {
    }

    public void updateSellIn() {
        item.handleIfExpired();
    }

}
