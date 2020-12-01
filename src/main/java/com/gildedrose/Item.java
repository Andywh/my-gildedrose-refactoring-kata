package com.gildedrose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int FAR_FROM_EXPIRY = 11;
    public static final int CLOSE_TO_EXPIRY = 6;

    enum IsAgedBrie {
        Yes, No
    }
    enum IsBackstagePasses {
        Yes, No
    }

    final String name;
    private int sellIn;
    private int quality;
    private IsAgedBrie isAgedBrie;
    private IsBackstagePasses isBackstagePasses;
    private boolean isSulfuras;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        isAgedBrie = name.equals(AGED_BRIE) ? IsAgedBrie.Yes : IsAgedBrie.No;
        isBackstagePasses = name.equals(BACKSTAGE_PASSES) ? IsBackstagePasses.Yes
                : IsBackstagePasses.No;
        isSulfuras = name.equals(SULFURAS);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    void increaseQualityIfNotMax() {
        if (quality < MAX_QUALITY) {
            quality++;
        }
    }

    void increaseQualityIfFarFromExpiry() {
        if (sellIn < FAR_FROM_EXPIRY) {
            increaseQualityIfNotMax();
        }
    }

    void increaseQualityIfCloseToExpiry() {
        if (sellIn < CLOSE_TO_EXPIRY) {
            increaseQualityIfNotMax();
        }
    }

    void increaseQualityOfBackstagePasses() {
        if (isBackstagePasses == IsBackstagePasses.Yes) {
            increaseQualityIfFarFromExpiry();
            increaseQualityIfCloseToExpiry();
        }
    }

    void increaseQualityIncludingBackstagePasses() {
        if (quality < MAX_QUALITY) {
            quality++;
            increaseQualityOfBackstagePasses();
        }
    }

    void decreaseQuality() {
        if (!isSulfuras) {
            quality--;
        }
    }

    void decreaseQualityIfItemHasQuality() {
        if (quality > 0) {
            decreaseQuality();
        }
    }

    void updateQuality() {
        if (isAgedBrie != IsAgedBrie.Yes && isBackstagePasses != IsBackstagePasses.Yes) {
            decreaseQualityIfItemHasQuality();
        } else {
            increaseQualityIncludingBackstagePasses();
        }
    }

    void handleExpiredBackstagePasses() {
        if (isBackstagePasses != IsBackstagePasses.Yes) {
            decreaseQualityIfItemHasQuality();
        } else {
            quality = 0;
        }
    }

    void handleExpired() {
        if (isAgedBrie != IsAgedBrie.Yes) {
            handleExpiredBackstagePasses();
        } else {
            increaseQualityIfNotMax();
        }
    }

    void handleIfExpired() {
        if (sellIn < 0) {
            handleExpired();
        }
    }

    void updateSellIn() {
        if (!isSulfuras) {
            sellIn--;
        }
        handleIfExpired();
    }

    void update() {
        updateQuality();
        updateSellIn();
    }

}
