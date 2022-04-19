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
    private int determineDegradeRate(Item item, boolean isExpired) {
        final int baseDegradeRate = item.name.equals(CONJURED) ? -2 : -1;
        return isExpired ? baseDegradeRate * 2 : baseDegradeRate;
    }


    private void updateBackstageQuality(Item item, boolean isExpired) {
        countQuality(item, 1);
        if (item.sellIn < 11 && item.sellIn < 6){
            countQuality(item, 1);
        }
        if (isExpired) {
            item.quality = item.quality - item.quality;
        }
    }

    public void updateItemQuality(Item item) {
        boolean isExpired = item.sellIn < 1;
        int degradeRate = determineDegradeRate(item, isExpired);
        boolean doesDegrade = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE) && !item.name.equals(SULFURAS);
        boolean hasSellByDate = !item.name.equals(SULFURAS);

        if (doesDegrade) {
            countQuality(item, degradeRate);
        }

        if (item.name.equals(AGED_BRIE)) {
            int adjustment = isExpired ? 2 : 1;
            countQuality(item, adjustment);
        }

        if (item.name.equals(BACKSTAGE)) {
            updateBackstageQuality(item, isExpired);
        }

        if (hasSellByDate) {
            item.sellIn = item.sellIn - 1;
        }
    }
}