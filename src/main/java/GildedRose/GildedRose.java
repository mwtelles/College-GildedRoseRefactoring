package GildedRose;

class GildedRose {
    Item[] items;

    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured Mana Cake";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    public void countQuality(Item item, int adjust) {
        int adjustQuality = item.quality + adjust;
        if (adjustQuality >= 0 && adjustQuality <= 50) {
            item.quality = adjustQuality;
        }
    }

    public void updateItemQuality(Item item) {
        int conjuredQuality = item.name.equals(CONJURED) ? -2 : -1;
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE) && !item.name.equals(SULFURAS)) {
            countQuality(item, conjuredQuality);
        } else {
            countQuality(item, 1);
            if (item.name.equals(BACKSTAGE) && item.sellIn < 11) {
                countQuality(item, 1);
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE)) {
                    if (!item.name.equals(SULFURAS)) {
                        countQuality(item, conjuredQuality);
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    countQuality(item, 1);
                }
            }
        }
    }
}