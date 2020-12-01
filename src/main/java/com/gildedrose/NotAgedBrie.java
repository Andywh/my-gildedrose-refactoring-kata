package com.gildedrose;

import static com.gildedrose.Item.BACKSTAGE_PASSES;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class NotAgedBrie implements IsAgedBrie {

    private IsBackstagePasses isBackstagePasses;

    public NotAgedBrie(Item item) {
        isBackstagePasses = item.name.equals(BACKSTAGE_PASSES) ? new BackstagePasses(item) : new NotBackstagePasses(item);
        isBackstagePasses.initializeSulfuras(item);
    }

    public void handleExpired() {
        isBackstagePasses.handleExpired();
    }

    public void updateQuality() {
        isBackstagePasses.updateQuality();
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
