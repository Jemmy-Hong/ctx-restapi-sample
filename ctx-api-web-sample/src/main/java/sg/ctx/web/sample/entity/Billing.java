package sg.ctx.web.sample.entity;

import java.util.List;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 17:46
 */
public class Billing {

    private List<String> daterange;
    private String currencyId;

    public List<String> getDaterange() {
        return daterange;
    }

    public void setDaterange(List<String> daterange) {
        this.daterange = daterange;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

}
