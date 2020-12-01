package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class BackstagePasses implements IsBackstagePasses {

    private IsSulfuras isSulfuras;

    private Item item;

    public BackstagePasses(Item item) {
        this.item = item;
    }

    public void handleExpired() {
        item.quality = 0;
    }

    public void updateQuality() {
        item.increaseQualityIncludingBackstagePasses();
    }

    public void increaseQuality() {
        item.increaseQualityIfFarFromExpiry();
        item.increaseQualityIfCloseToExpiry();
    }

    public void initializeSulfuras(Item item) {
        isSulfuras = new NotSulfuras(item);
    }

    @Override
    public void initializeNotSulfuras() {
        isSulfuras = new NotSulfuras(item);
    }

    public void decreaseQuality() {
        isSulfuras.decreaseQuality();
    }

    public void updateSellIn() {
        isSulfuras.updateSellIn();
    }

}
