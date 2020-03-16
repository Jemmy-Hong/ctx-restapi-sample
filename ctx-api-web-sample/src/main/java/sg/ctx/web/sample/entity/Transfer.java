package sg.ctx.web.sample.entity;

import java.util.Date;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 18:01
 */
public class Transfer {

    private Date startDate;

    private Date endDate;

    private String transferType;

    private String currencyType;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
}
