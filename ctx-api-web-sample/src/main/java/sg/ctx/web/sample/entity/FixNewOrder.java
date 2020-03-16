package sg.ctx.web.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 11:16
 */
public class FixNewOrder implements Serializable {

    private String clOrdID;

    private String symbol;

    private String account;

    private Long orderQty;

    private String ordType;

    private BigDecimal price;

    private String side;

    private String triggerType;

    private BigDecimal triggerPrice;

    private String triggerPriceType;

    private String triggerPriceDirection;

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }

    public String getOrdType() {
        return ordType;
    }

    public void setOrdType(String ordType) {
        this.ordType = ordType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public BigDecimal getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(BigDecimal triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public String getTriggerPriceType() {
        return triggerPriceType;
    }

    public void setTriggerPriceType(String triggerPriceType) {
        this.triggerPriceType = triggerPriceType;
    }

    public String getTriggerPriceDirection() {
        return triggerPriceDirection;
    }

    public void setTriggerPriceDirection(String triggerPriceDirection) {
        this.triggerPriceDirection = triggerPriceDirection;
    }
}
