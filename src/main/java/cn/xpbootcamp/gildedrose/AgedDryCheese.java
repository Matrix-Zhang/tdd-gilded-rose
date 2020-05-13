package cn.xpbootcamp.gildedrose;

public class AgedDryCheese extends Commodity {
    AgedDryCheese(int sellIn, int quantity) {
        super(sellIn, quantity, true);
    }

    @Override
    public int reduceEveryDayBeforeSellInDate() {
        return -1;
    }

    @Override
    public int reduceEveryDayAfterSellInDate() {
        return -2;
    }
}
