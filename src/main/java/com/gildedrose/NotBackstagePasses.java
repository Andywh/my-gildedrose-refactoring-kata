package com.gildedrose;

import static com.gildedrose.Item.SULFURAS;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class NotBackstagePasses implements IsBackstagePasses {

    private IsSulfuras isSulfuras;

    private Item item;

    public NotBackstagePasses(Item item) {
        this.item = item;
    }

    public void handleExpired() {
        item.decreaseQualityIfItemHasQuality();
    }

    public void updateQuality() {
        item.decreaseQualityIfItemHasQuality();
    }

    public void increaseQuality() {
    }

    public void initializeSulfuras(Item item) {
        isSulfuras = item.name.equals(SULFURAS) ? new Sulfuras(item) : new NotSulfuras(item);
    }

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
