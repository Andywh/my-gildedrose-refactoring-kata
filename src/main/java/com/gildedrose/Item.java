package com.gildedrose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int FAR_FROM_EXPIRY = 11;
    public static final int CLOSE_TO_EXPIRY = 6;

    interface IsAgedBrie {
        boolean isYes();

        void handleExpired();
    }

    class AgedBrie implements IsAgedBrie {

        @Override
        public boolean isYes() {
            return true;
        }

        public void handleExpired() {
            increaseQualityIfNotMax();
        }

    }

    class NotAgedBrie implements IsAgedBrie {

        @Override
        public boolean isYes() {
            return false;
        }

        public void handleExpired() {
            isBackstagePasses.handleExpiredBackstagePasses();
        }

    }

    interface IsBackstagePasses {
        boolean isYes();

        boolean isNo();

        void handleExpiredBackstagePasses();
    }

    class BackstagePasses implements IsBackstagePasses {

        @Override
        public boolean isYes() {
            return true;
        }

        @Override
        public boolean isNo() {
            return false;
        }

        public void handleExpiredBackstagePasses() {
            quality = 0;
        }

    }

    class NotBackstagePasses implements IsBackstagePasses {

        @Override
        public boolean isYes() {
            return false;
        }

        @Override
        public boolean isNo() {
            return false;
        }

        public void handleExpiredBackstagePasses() {
            decreaseQualityIfItemHasQuality();
        }

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
        isAgedBrie = name.equals(AGED_BRIE) ? new AgedBrie() : new NotAgedBrie();
        isBackstagePasses = name.equals(BACKSTAGE_PASSES) ? new BackstagePasses() : new NotBackstagePasses();
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
        if (isBackstagePasses.isYes()) {
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
        if (notBrieAndNotBackstagePasses()) {
            decreaseQualityIfItemHasQuality();
        } else {
            increaseQualityIncludingBackstagePasses();
        }
    }

    private boolean notBrieAndNotBackstagePasses() {
        return !isAgedBrie.isYes() && !isBackstagePasses.isYes();
    }

    void handleIfExpired() {
        if (sellIn < 0) {
            isAgedBrie.handleExpired();
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
