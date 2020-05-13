package cn.xpbootcamp.gildedrose;

import java.time.LocalDate;
import java.time.Period;

public class GeneralCommodity extends Commodity {
    GeneralCommodity(int sellIn, int quantity) {
        super(sellIn, quantity);
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
