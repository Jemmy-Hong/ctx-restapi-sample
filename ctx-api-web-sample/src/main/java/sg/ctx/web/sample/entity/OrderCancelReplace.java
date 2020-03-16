package sg.ctx.web.sample.entity;

import java.math.BigDecimal;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 17:50
 */
public class OrderCancelReplace {

    private String clOrdID;

    private String origClOrdID;

    private String account;

    private String execInst;

    private BigDecimal orderQty;

    private BigDecimal displayQty;

    private BigDecimal minQty;

    private Integer minQtyMethod;

    private String ordType;

    private BigDecimal price;

    private BigDecimal yield;

    private String timeInForce;

    private Integer expireDate;

    private String positionEffect;

    private String senderCompId;

    private String securitySubType;

    private Integer securityID;

    private String securityIDSource;

    private String securityGroup;

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

    public String getOrigClOrdID() {
        return origClOrdID;
    }

    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getExecInst() {
        return execInst;
    }

    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getDisplayQty() {
        return displayQty;
    }

    public void setDisplayQty(BigDecimal displayQty) {
        this.displayQty = displayQty;
    }

    public BigDecimal getMinQty() {
        return minQty;
    }

    public void setMinQty(BigDecimal minQty) {
        this.minQty = minQty;
    }

    public Integer getMinQtyMethod() {
        return minQtyMethod;
    }

    public void setMinQtyMethod(Integer minQtyMethod) {
        this.minQtyMethod = minQtyMethod;
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

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public Integer getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Integer expireDate) {
        this.expireDate = expireDate;
    }

    public String getPositionEffect() {
        return positionEffect;
    }

    public void setPositionEffect(String positionEffect) {
        this.positionEffect = positionEffect;
    }

    public String getSenderCompId() {
        return senderCompId;
    }

    public void setSenderCompId(String senderCompId) {
        this.senderCompId = senderCompId;
    }

    public String getSecuritySubType() {
        return securitySubType;
    }

    public void setSecuritySubType(String securitySubType) {
        this.securitySubType = securitySubType;
    }

    public Integer getSecurityID() {
        return securityID;
    }

    public void setSecurityID(Integer securityID) {
        this.securityID = securityID;
    }

    public String getSecurityIDSource() {
        return securityIDSource;
    }

    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
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
