package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class AgedBrie extends GeneralItem {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    void updateQuality() {
        item.increaseQualityIfNotMax();
    }

    @Override
    void handleExpire() {
        item.increaseQualityIfNotMax();
    }
}
