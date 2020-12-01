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

        void increaseQuality();

        void decreaseQuality();

        void updateSellIn();
    }

    class AgedBrie implements IsAgedBrie {

        private IsBackstagePasses isBackstagePasses;

        public void handleExpired() {
            increaseQualityIfNotMax();
        }

        public void updateQuality() {
            increaseQualityIncludingBackstagePasses();
        }

        public void initializeBackstagePassesAndSulfuras(String name) {
            isBackstagePasses = new NotBackstagePasses();
            isBackstagePasses.initializeNotSulfuras();
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

    class NotAgedBrie implements IsAgedBrie {

        private IsBackstagePasses isBackstagePasses;

        public void handleExpired() {
            isBackstagePasses.handleExpired();
        }

        public void updateQuality() {
            isBackstagePasses.updateQuality();
        }

        public void initializeBackstagePassesAndSulfuras(String name) {
            isBackstagePasses = name.equals(BACKSTAGE_PASSES) ? new BackstagePasses() : new NotBackstagePasses();
            isBackstagePasses.initializeSulfuras(name);
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

    interface IsBackstagePasses {

        void handleExpired();

        void updateQuality();

        void increaseQuality();

        void initializeSulfuras(String name);

        void initializeNotSulfuras();

        void decreaseQuality();

        void updateSellIn();
    }

    class BackstagePasses implements IsBackstagePasses {

        private IsSulfuras isSulfuras;

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

        public void initializeNotSulfuras() {
            isSulfuras = new NotSulfuras();
        }

        public void decreaseQuality() {
            isSulfuras.decreaseQuality();
        }

        public void updateSellIn() {
            isSulfuras.updateSellIn();
        }

    }

    class NotBackstagePasses implements IsBackstagePasses {

        private IsSulfuras isSulfuras;


        public void handleExpired() {
            decreaseQualityIfItemHasQuality();
        }

        public void updateQuality() {
            decreaseQualityIfItemHasQuality();
        }

        public void increaseQuality() {
        }

        public void initializeSulfuras(String name) {
            isSulfuras = name.equals(SULFURAS) ? new Sulfuras() : new NotSulfuras();
        }

        public void initializeNotSulfuras() {
            isSulfuras = new NotSulfuras();
        }

        public void decreaseQuality() {
            isSulfuras.decreaseQuality();
        }

        public void updateSellIn() {
            isSulfuras.updateSellIn();
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
            handleIfExpired();
        }

    }

    class NotSulfuras implements IsSulfuras {

        public void decreaseQuality() {
            quality--;
        }

        public void updateSellIn() {
            sellIn--;
            handleIfExpired();
        }

    }

    final String name;
    private int sellIn;
    private int quality;
    private IsAgedBrie isAgedBrie;

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
