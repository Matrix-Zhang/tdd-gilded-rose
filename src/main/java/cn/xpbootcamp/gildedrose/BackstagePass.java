package cn.xpbootcamp.gildedrose;

import java.time.LocalDate;
import java.time.Period;

public class BackstagePass extends Commodity implements CommodityQuantity {
    BackstagePass(int sellIn, int quantity) {
        super(sellIn, quantity, true);
    }

    @Override
    public int reduceEveryDayBeforeSellInDate() {
        return 0;
    }

    @Override
    public int reduceEveryDayAfterSellInDate() {
        return 0;
    }

    @Override
    public int calculateQuantityByDate(LocalDate date) {
        int res = this.quantity;

        if(date.isAfter(this.sellInDate)) {
            res = 0;
        } else {
            int beforeSellInDays = Period.between(date, this.sellInDate).getDays();
            if (beforeSellInDays < 10 && beforeSellInDays > 5) {
                 res  +=  (10 - beforeSellInDays) * 2;
            } else if (beforeSellInDays < 5) {
                res += (5 - beforeSellInDays) * 3;
            }
        }
        return Math.min(50, Math.max(0, res));
    }
}
