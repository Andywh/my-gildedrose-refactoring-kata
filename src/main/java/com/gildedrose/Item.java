package com.gildedrose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int FAR_FROM_EXPIRY = 11;
    public static final int CLOSE_TO_EXPIRY = 6;

    final String name;
    public int sellIn;
    public int quality;
    public IsAgedBrie isAgedBrie;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        isAgedBrie = name.equals(AGED_BRIE) ? new AgedBrie(this) : new NotAgedBrie(this);
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

    void increaseQualityIncludingBackstagePasses() {
        if (quality < MAX_QUALITY) {
            quality++;
            isAgedBrie.increaseQuality();
        }
    }

    void decreaseQualityIfItemHasQuality() {
        if (quality > 0) {
            decreaseQuality();
        }
    }

    private void decreaseQuality() {
        isAgedBrie.decreaseQuality();
    }

    void handleIfExpired() {
        if (sellIn < 0) {
            isAgedBrie.handleExpired();
        }
    }

    void update() {
        isAgedBrie.updateQuality();
        updateSellIn();
    }

    private void updateSellIn() {
        isAgedBrie.updateSellIn();
    }

}
