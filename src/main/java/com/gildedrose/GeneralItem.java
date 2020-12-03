package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class GeneralItem {

    public Item item;

    public GeneralItem(Item item) {
        this.item = item;
    }

    void updateQuality() {
        item.decreaseQualityIfNotZero();
    }

    void updateSellIn() {
        item.sellIn--;
    }

    void handleExpire2() {
        if (item.sellIn < 0) {
            handleExpire();
        }
    }

    void handleExpire() {
        item.decreaseQualityIfNotZero();
    }

}
