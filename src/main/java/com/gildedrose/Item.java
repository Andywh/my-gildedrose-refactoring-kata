package com.gildedrose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

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
    private boolean isHandOfSulfuras;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        isAgedBrie = name.equals(AGED_BRIE) ? IsAgedBrie.Yes : IsAgedBrie.No;
        isBackstagePasses = name.equals(BACKSTAGE_PASSES) ? IsBackstagePasses.Yes
                : IsBackstagePasses.No;
        isHandOfSulfuras = name.equals(SULFURAS);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    void increaseQuality() {
        quality = quality + 1;
    }

    void increaseQualityIfNotMax() {
        if (quality < 50) {
            increaseQuality();
        }
    }

    void increaseQualityIfFarFromExpiry() {
        if (sellIn < 11) {
            increaseQualityIfNotMax();
        }
    }

    void increaseQualityIfCloseToExpiry() {
        if (sellIn < 6) {
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
        if (quality < 50) {
            increaseQuality();
            increaseQualityOfBackstagePasses();
        }
    }

    void decreaseQuality() {
        if (!isHandOfSulfuras) {
            quality = quality - 1;
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
        if (!isHandOfSulfuras) {
            sellIn = sellIn - 1;
        }
        handleIfExpired();
    }

    void update() {
        updateQuality();
        updateSellIn();
    }

}
