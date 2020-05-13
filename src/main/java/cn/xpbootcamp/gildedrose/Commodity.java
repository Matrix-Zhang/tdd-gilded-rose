package cn.xpbootcamp.gildedrose;

import java.time.LocalDate;
import java.time.Period;

interface CommodityQuantity {
    public int calculateQuantityByDate(LocalDate date);
}

public abstract class Commodity implements CommodityQuantity {
    public int sellIn;
    public int quantity;
    public Boolean hasSellIn;
    public LocalDate productionDate;
    public LocalDate sellInDate;

    Commodity(int sellIn, int quantity, Boolean hasSellIn) {
        if (hasSellIn && sellIn <= 0) {
            throw new IllegalArgumentException("sellIn must bigger than 1.");
        }

        if (quantity < 0 || quantity > 50) {
            throw new IllegalArgumentException("quantity must between (0, 50].");
        }

        this.sellIn = sellIn;
        this.quantity = quantity;
        this.hasSellIn = hasSellIn;
        this.productionDate = LocalDate.now();
        this.sellInDate = this.productionDate.plusDays(sellIn);
    }

    public abstract int reduceEveryDayBeforeSellInDate();

    public abstract int reduceEveryDayAfterSellInDate();

    public int calculateQuantityByDate(LocalDate date) {
        int reduce = 0;

        if (date.isBefore(this.productionDate)) {
            throw new IllegalArgumentException("the param date must not before production date");
        }

        if (date.isAfter(this.sellInDate)) {
            reduce = Period.between(this.productionDate, this.sellInDate).getDays() * reduceEveryDayBeforeSellInDate() + Period.between(this.sellInDate, date).getDays() * reduceEveryDayAfterSellInDate();
        } else {
            reduce = Period.between(this.productionDate, date).getDays() * reduceEveryDayBeforeSellInDate();
        }

        return Math.min(50, Math.max(this.quantity - reduce, 0));
    }
}
