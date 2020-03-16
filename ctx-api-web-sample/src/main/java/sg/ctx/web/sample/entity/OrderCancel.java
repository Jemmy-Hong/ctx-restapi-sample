package sg.ctx.web.sample.entity;

import java.math.BigDecimal;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 17:55
 */
public class OrderCancel {

    private String clOrdID;

    private String origClOrdID;

    private String senderCompId;

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

    public String getSenderCompId() {
        return senderCompId;
    }

    public void setSenderCompId(String senderCompId) {
        this.senderCompId = senderCompId;
    }
}
