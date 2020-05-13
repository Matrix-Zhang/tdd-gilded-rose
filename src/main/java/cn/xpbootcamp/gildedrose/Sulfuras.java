package cn.xpbootcamp.gildedrose;

public class Sulfuras extends Commodity {
    Sulfuras(int quantity) {
        super(0, quantity, false);
    }

    @Override
    public int reduceEveryDayBeforeSellInDate() {
        return 0;
    }

    @Override
    public int reduceEveryDayAfterSellInDate() {
        return 0;
    }
}
