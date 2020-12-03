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
        item.decreaseQualityIfHasQuality();
    }

    void updateSellIn() {
        item.decreaseSellIn();
    }

    void handleIfExpire() {
        if (item.sellIn < 0) {
            handleExpire();
        }
    }

    void handleExpire() {
        item.decreaseQualityIfHasQuality();
    }

}
