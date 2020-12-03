package com.gildedrose;

public class Item {

    public static final int MAX_QUALITY = 50;

    public String name;

    public int sellIn;

    public int quality;

    private GeneralItem item;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        item = GoodsFactory.buildItem(name, this);
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

    void decreaseQualityIfNotZero() {
        if (quality > 0) {
            quality--;
        }
    }

    public void updateQuality() {
        item.updateQuality();
        item.updateSellIn();
        item.handleExpire2();
    }
}
