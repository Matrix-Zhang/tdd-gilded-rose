package cn.xpbootcamp.gildedrose;

public class GeneralCommodity extends Commodity {
    GeneralCommodity(int sellIn, int quantity) {
        super(sellIn, quantity, true);
    }

    @Override
    public int reduceEveryDayBeforeSellInDate() {
        return 1;
    }

    @Override
    public int reduceEveryDayAfterSellInDate() {
        return 2;
    }
}
