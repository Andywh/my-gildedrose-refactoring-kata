package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-02.
 */
public class Backstage extends GeneralItem {

    public static final int FAR_FROM_EXPIRE = 11;
    public static final int CLOSE_TO_EXPIRE = 6;

    public Backstage(Item item) {
        super(item);
    }

    @Override
    void updateQuality() {
        item.increaseQualityIfNotMax();
        increaseIfFarFromExpiry();
        increaseIfCloseToExpiry();
    }

    @Override
    void handleExpire() {
        item.quality = 0;
    }

    public void increaseIfFarFromExpiry() {
        incraseIfSellInLessThan(FAR_FROM_EXPIRE);
    }

    public void increaseIfCloseToExpiry() {
        incraseIfSellInLessThan(CLOSE_TO_EXPIRE);
    }

    private void incraseIfSellInLessThan(int expireTime) {
        if (item.sellIn < expireTime) {
            item.increaseQualityIfNotMax();
        }
    }

}
