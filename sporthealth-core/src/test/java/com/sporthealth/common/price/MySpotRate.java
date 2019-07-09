package com.sporthealth.common.price;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-07-08 23:00
 **/
public class MySpotRate {
    public enum PriceState{
        Init(0),
        Normal(1),
        AbNormal(2);
        private int value;
        PriceState(int value) {
            this.value = value;
        }
    }
    private double bid = 0.0;
    private double offer = 0.0;
    private double mid = 0.0;
    private double quoteunit = 1.0;
    private PriceState priceState = PriceState.Init;

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    public double getQuoteunit() {
        return quoteunit;
    }

    public void setQuoteunit(double quoteunit) {
        this.quoteunit = quoteunit;
    }

    public PriceState getPriceState() {
        return priceState;
    }

    public void setPriceState(PriceState priceState) {
        this.priceState = priceState;
    }

    @Override
    public String toString() {
        return "MySpotRate{" +
                "bid=" + bid +
                ", offer=" + offer +
                ", mid=" + mid +
                ", quoteunit=" + quoteunit +
                ", priceState=" + priceState +
                '}';
    }
}
