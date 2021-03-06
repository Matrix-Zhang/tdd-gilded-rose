package cn.xpbootcamp.gildedrose;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class CommodityTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_throw_exception_when_given_illegal_sell_in() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("sellIn must bigger than 1.");
        new GeneralCommodity(0, 1);
    }

    @Test
    public void should_throw_exception_when_given_illegal_quantity() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("quantity must between (0, 50].");
        new GeneralCommodity(1, -1);
        new GeneralCommodity(1, 51);
    }

    @Test
    public void should_throw_exception_when_date_is_before_production_date_then_calculate_quantity() {
        GeneralCommodity generalCommodity = new GeneralCommodity(1, 1);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("the param date must not before production date");
        generalCommodity.calculateQuantityByDate(LocalDate.now().minusDays(1));
    }

    @Test
    public void general_commodity_should_reduce_quantity_1_every_day_before_the_sell_in_date() {
        int sellIn = 30;
        int quantity = 50;

        GeneralCommodity generalCommodity = new GeneralCommodity(sellIn, quantity);
        for (int i = 0; i <= sellIn; i++) {
            assertEquals(quantity - i, generalCommodity.calculateQuantityByDate(LocalDate.now().plusDays(i)));
        }
    }

    @Test
    public void general_commodity_should_reduce_quantity_2_every_day_out_the_sell_in_period() {
        int sellIn = 10;
        int quantity = 50;

        GeneralCommodity generalCommodity = new GeneralCommodity(sellIn, quantity);
        int quantityAtSellInDate = generalCommodity.calculateQuantityByDate(LocalDate.now().plusDays(sellIn));

        for (int i = 1; i <= 5; i++) {
            assertEquals(quantityAtSellInDate - i * 2, generalCommodity.calculateQuantityByDate(LocalDate.now().plusDays(sellIn + i)));
        }
    }

    @Test
    public void aged_dry_cheese_should_grown_quantity_1_every_day_before_the_sell_in_date() {
        int sellIn = 30;
        int quantity = 20;

        AgedDryCheese agedDryCheese = new AgedDryCheese(sellIn, quantity);
        for (int i = 0; i <= sellIn; i++) {
            assertEquals(quantity + i, agedDryCheese.calculateQuantityByDate(LocalDate.now().plusDays(i)));
        }
    }

    @Test
    public void aged_dry_cheese_should_grown_quantity_2_every_day_after_the_sell_in_date() {
        int sellIn = 10;
        int quantity = 10;

        AgedDryCheese agedDryCheese = new AgedDryCheese(sellIn, quantity);
        int quantityAtSellInDate = agedDryCheese.calculateQuantityByDate(LocalDate.now().plusDays(sellIn));

        for (int i = 0; i <= sellIn; i++) {
            assertEquals(quantityAtSellInDate + i * 2, agedDryCheese.calculateQuantityByDate(LocalDate.now().plusDays(sellIn + i)));
        }
    }

    @Test
    public void sulfuras_quantity_should_never_change_when_calculate_quantity() {
        Sulfuras sulfuras = new Sulfuras(30);
        for (int i = 0; i < 100; i++) {
            assertEquals(30, sulfuras.calculateQuantityByDate(LocalDate.now().plusDays(i)));
        }
    }

    @Test
    public void backstage_pass_quantity_should_grown_2_every_day_when_5_10_days_before_sell_in_date() {
        int sellIn = 20;
        BackstagePass backstagePass = new BackstagePass(sellIn, 10);
        LocalDate date = LocalDate.now().plusDays(sellIn).minusDays(10);
        int quantity = backstagePass.calculateQuantityByDate(date);
        for (int i = 1; i < 5; i++) {
            assertEquals(quantity + i * 2, backstagePass.calculateQuantityByDate(date.plusDays(i)));
        }
    }

    @Test
    public void backstage_pass_quantity_should_grown_3_every_day_when_1_5_days_before_sell_in_date() {
        int sellIn = 20;
        BackstagePass backstagePass = new BackstagePass(sellIn, 10);
        LocalDate date = LocalDate.now().plusDays(sellIn).minusDays(5);
        int quantity = backstagePass.calculateQuantityByDate(date);
        for (int i = 1; i < 5; i++) {
            assertEquals(quantity + i * 3, backstagePass.calculateQuantityByDate(date.plusDays(i)));
        }
    }

    @Test
    public void backstage_pass_quantity_should_be_0_when_days_after_sell_in_date() {
        int sellIn = 20;
        BackstagePass backstagePass = new BackstagePass(sellIn, 10);
        for (int i = 1; i < 5; i++) {
            assertEquals(0, backstagePass.calculateQuantityByDate(LocalDate.now().plusDays(sellIn).plusDays(i)));
        }
    }
}
