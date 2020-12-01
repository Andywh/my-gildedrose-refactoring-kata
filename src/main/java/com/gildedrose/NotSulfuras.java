package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class NotSulfuras implements IsSulfuras {

    private Item item;

    public NotSulfuras(Item item) {
        this.item = item;
    }

    public void decreaseQuality() {
        item.quality--;
    }

    public void updateSellIn() {
        item.sellIn--;
        item.handleIfExpired();
    }

}
