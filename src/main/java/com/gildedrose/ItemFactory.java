package com.gildedrose;

/**
 * Created by Ai Lun on 2020-12-03.
 */
public class ItemFactory {

    private static final String BACK_STAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static GeneralItem buildItem(String name, Item item) {
        if (name.equals(BACK_STAGE)) {
            return new Backstage(item);
        } else if (name.equals(AGED_BRIE)) {
            return new AgedBrie(item);
        } else if (name.equals(SULFURAS)) {
            return new Sulfuras(item);
        } else {
            return new GeneralItem(item);
        }
    }

}
