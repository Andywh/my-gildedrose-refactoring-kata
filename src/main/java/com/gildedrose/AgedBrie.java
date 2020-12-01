package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class AgedBrie implements IsAgedBrie {

    private IsBackstagePasses isBackstagePasses;

    private Item item;

    public AgedBrie(Item item) {
        isBackstagePasses = new NotBackstagePasses(item);
        isBackstagePasses.initializeNotSulfuras();
        this.item = item;
    }

    public void handleExpired() {
        item.increaseQualityIfNotMax();
    }

    public void updateQuality() {
        item.increaseQualityIncludingBackstagePasses();
    }

    public void increaseQuality() {
        isBackstagePasses.increaseQuality();
    }

    public void decreaseQuality() {
        isBackstagePasses.decreaseQuality();
    }

    public void updateSellIn() {
        isBackstagePasses.updateSellIn();
    }

}
