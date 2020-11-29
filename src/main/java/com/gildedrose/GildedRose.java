package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     *   Rule
     *   1. 陈年干酪（Aged Brie）是一种特殊的商品，放得越久，价值反而越高。
     *   2. 商品的价值永远不会小于0，也永远不会超过50。
     *   3. 萨弗拉斯（Sulfuras）是一种传奇商品，没有保质期的概念，质量也不会下滑。
     *   4. 后台门票（Backstage pass）和陈年干酪有相似之处：越是接近演出日，随着"SellIn"值的增加，
     *   商品价值"Quality"值反而上升。在演出前10天，价值每天上升2点；
     *   演出前5天，价值每天上升3点。但一旦过了演出日，价值就马上变成0。
     *
     *
     */
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i].update();
        }
    }

}