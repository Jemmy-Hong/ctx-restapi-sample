package sg.ctx.web.sample.entity;

import java.util.List;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 17:59
 */
public class LedgerOrder {

    private List<String> daterange;
    private String orderBookIndex;

    public List<String> getDaterange() {
        return daterange;
    }

    public void setDaterange(List<String> daterange) {
        this.daterange = daterange;
    }

    public String getOrderBookIndex() {
        return orderBookIndex;
    }

    public void setOrderBookIndex(String orderBookIndex) {
        this.orderBookIndex = orderBookIndex;
    }
}
