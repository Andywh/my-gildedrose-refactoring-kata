package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
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
        if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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
        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
            quality = quality - 1;
        }
    }

    void decreaseQualityIfItemHasQuality() {
        if (quality > 0) {
            decreaseQuality();
        }
    }

    void updateQuality() {
        if (!name.equals("Aged Brie") && !name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            decreaseQualityIfItemHasQuality();
        } else {
            increaseQualityIncludingBackstagePasses();
        }
    }

    void handleExpiredBackstagePasses() {
        if (!name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            decreaseQualityIfItemHasQuality();
        } else {
            quality = 0;
        }
    }

    void handleExpired() {
        if (!name.equals("Aged Brie")) {
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
        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
            sellIn = sellIn - 1;
        }
        handleIfExpired();
    }

}
