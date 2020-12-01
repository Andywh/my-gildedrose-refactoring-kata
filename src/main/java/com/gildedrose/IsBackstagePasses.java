package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public interface IsBackstagePasses {

    void handleExpired();

    void updateQuality();

    void increaseQuality();

    void initializeSulfuras(Item item);

    void initializeNotSulfuras();

    void decreaseQuality();

    void updateSellIn();

}
