package com.gildedrose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int FAR_FROM_EXPIRY = 11;
    public static final int CLOSE_TO_EXPIRY = 6;

    interface IsAgedBrie {


        void handleExpired();

        void updateQuality();

        void initializeBackstagePassesAndSulfuras(String name);

        void handleIfExpired();
    }

    class AgedBrie implements IsAgedBrie {

        public void handleExpired() {
            increaseQualityIfNotMax();
        }

        public void updateQuality() {
            increaseQualityIncludingBackstagePasses();
        }

        public void initializeBackstagePassesAndSulfuras(String name) {
            isBackstagePasses = new NotBackstagePasses();
            isSulfuras = new NotSulfuras();
        }

        public void handleIfExpired() {
            if (sellIn < 0) {
                handleExpired();
            }
        }
    }

    class NotAgedBrie implements IsAgedBrie {

        public void handleExpired() {
            isBackstagePasses.handleExpired();
        }

        public void updateQuality() {
            isBackstagePasses.updateQuality();
        }

        public void initializeBackstagePassesAndSulfuras(String name) {
            isBackstagePasses = name.equals(BACKSTAGE_PASSES) ? new BackstagePasses() : new NotBackstagePasses();
            initializeSulfuras(name);
        }

        public void initializeSulfuras(String name) {
            isBackstagePasses.initializeSulfuras(name);
        }

        public void handleIfExpired() {
            if (sellIn < 0) {
                handleExpired();
            }
        }

    }

    interface IsBackstagePasses {

        void handleExpired();

        void updateQuality();

        void increaseQuality();

        void initializeSulfuras(String name);
    }

    class BackstagePasses implements IsBackstagePasses {

        @Override
        public void handleExpired() {
            quality = 0;
        }

        public void updateQuality() {
            increaseQualityIncludingBackstagePasses();
        }

        public void increaseQuality() {
            increaseQualityIfFarFromExpiry();
            increaseQualityIfCloseToExpiry();
        }

        public void initializeSulfuras(String name) {
            isSulfuras = new NotSulfuras();
        }
    }

    class NotBackstagePasses implements IsBackstagePasses {

        @Override
        public void handleExpired() {
            decreaseQualityIfHasQuality();
        }

        public void updateQuality() {
            decreaseQualityIfHasQuality();
        }

        public void increaseQuality() {

        }

        public void initializeSulfuras(String name) {
            isSulfuras = name.equals(SULFURAS) ? new Sulfuras() : new NotSulfuras();
        }

    }

    interface IsSulfuras {
        void decreaseQuality();

        void updateSellIn();
    }

    class Sulfuras implements IsSulfuras {

        public void decreaseQuality() {
        }

        public void updateSellIn() {
            isAgedBrie.handleExpired();
        }

    }

    class NotSulfuras implements IsSulfuras {

        public void decreaseQuality() {
            quality--;
        }

        public void updateSellIn() {
            sellIn--;
            isAgedBrie.handleExpired();
        }
    }

    public String name;
    public int sellIn;
    public int quality;
    private IsAgedBrie isAgedBrie;
    private IsBackstagePasses isBackstagePasses;
    private IsSulfuras isSulfuras;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        isAgedBrie = name.equals(AGED_BRIE) ? new AgedBrie() : new NotAgedBrie();
        isAgedBrie.initializeBackstagePassesAndSulfuras(name);
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
        if (this.sellIn < CLOSE_TO_EXPIRY) {
            increaseQualityIfNotMax();
        }
    }

    void increaseQualityIncludingBackstagePasses() {
        if (this.quality < MAX_QUALITY) {
            quality++;
            increaseQuality();
        }
    }

    private void increaseQuality() {
        isBackstagePasses.increaseQuality();
    }

    void decreaseQualityIfHasQuality() {
        if (quality > 0) {
            isSulfuras.decreaseQuality();
        }
    }

    void update() {
        isAgedBrie.updateQuality();
        isSulfuras.updateSellIn();
    }

}
