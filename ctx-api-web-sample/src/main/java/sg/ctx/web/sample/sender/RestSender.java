package sg.ctx.web.sample.sender;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import sg.ctx.web.sample.entity.*;
import sg.ctx.web.sample.utils.ConverUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * use this class to send a safe certificate request
 * @author Jemmy Hong
 * @date 2020/3/12 11:24
 */
public class RestSender {

    private static final String REQUEST_URL = "http://localhost:9033/account/transfer/history";
    private static final String APPLICATION_ID = "8545f045-f2d7-451f-9563-602c7c23deab";
    private static final String CLIENT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJjFrlebgAyhX4MY+zOaVLvXHf05e/swX6cZR3ixzbyxD8fquq9x4c1pukkbDWOyxuOa/FCXiIc7eOQa5F6sxkZe+SDTNshfVa+KpoS4RkXGaUD1CfpVH/PO6+Wht2wDiFDhGk+K6iRP+KUOz2MEkNQICAfR4lArNcAUqXzWfGTFAgMBAAECgYBf5zDT1E0lIKSp0qPXOBnbNWahEVYQUMSrTEY+sTebLXffoy4Adzgt0CJeRDo6jP8n4KJmIUpWwnHYmD0mZcWtbP4cnv+F7zmiB3Lk8aMI7ppqbTbqYGqQk9JtTK7c3SZS3iYKdPDoB1I+6D/UWbWofgXZeaXc0MKxLPpK6wxjYQJBAMy2gAT2zUJYxKcu0ddasn2co5/vHMa4gEZUdWdGxNlA8seA5cTZndEFGtsKBygGltymGf/kxrjEoENLT1QiEx0CQQC/C+dUsUMSYzOStmAlqQwIx64RKl/dqRYYE4OsRG0fHuPcuZ+RPU19eZI2tz+p+dNp7dOQfxYnEl/nzA4tQH/JAkBrw9b4xgOf3aMIb3k/6Rr1TTRFyVZYtl+wpl13lKWjyLEFnTFXw0d09T8B9/F0fKOGXFprfz+tnLTROB/2pklxAkEAt84vEce9/RneS6AVnwYmYugia7d2JmAAtsdHntzyk8d3eSxJ/kWZoUfuyZ/Cq7mFktvS+amb2ZmPFge3+AefKQJAFwQaPEEspJQK7OkuDT7WboJHMgcfG5wxABnngYIEERdYHO3c8n+0pjUpSlLL9PK1A18+sSrJ1bbzmy3p4lOD1A==";

    public static void main(String[] args) {
        // FixNewOrder order = getFixNewOrder(); //passed
        // Billing billing = getBilling(); //passed
        // LedgerOrder ledgerOrder = getLedgerOrder(); //passed
        // OrderCancel orderCancel = getOrderCancel(); //passed
        // OrderCancelReplace orderCancelReplace = getOrderCancelReplace(); //passed
        // OrderReport orderReport = getOrderReport(); //passed
        // Statement statement = getStatement(); //passed
        // Trade trade = getTrade();
        Transfer transfer = getTransfer(); // passed

        JSONObject encryptSendingObj = ConverUtil.getEncryptSendingJSON(APPLICATION_ID, CLIENT_PRIVATE_KEY, transfer);
        sendEncryptRequest(REQUEST_URL, encryptSendingObj);
    }

    private static Transfer getTransfer() {
        Transfer transfer = new Transfer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            transfer.setStartDate(sdf.parse("2020-02-01"));
            transfer.setEndDate(sdf.parse("2020-03-15"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transfer.setTransferType("ALL");
        transfer.setCurrencyType(null);
        return transfer;
    }

    @NotNull
    private static Trade getTrade() {
        Trade trade = new Trade();
        trade.setDaterange(Arrays.asList("2020-02-01","2020-03-30"));
        trade.setOrderBookIndex("");
        return trade;
    }


    public static void sendEncryptRequest(String url, JSONObject encryptRequest){
        // send a get request
        // sendGetRequest(url);

        // send a post request
        sendPostRquest(url, encryptRequest);
    }

    /**
     * post request
     * @param url
     * @param encryptRequest
     */
    private static void sendPostRquest(String url, JSONObject encryptRequest) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONObject.toJSONString(encryptRequest), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("applicationId", encryptRequest.getString("applicationId"))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("\n----------------------------------->return result");
                System.out.println(response.body().string());
            }
        });
    }

    /**
     * send get request
     * @param url
     */
    private static void sendGetRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = client.newCall(request);
    }

    @NotNull
    private static FixNewOrder getFixNewOrder() {
        FixNewOrder order = new FixNewOrder();
        order.setClOrdID("202003120038");
        order.setSymbol("BTC/SGD");
        order.setAccount("FIRM01001");
        order.setOrderQty(1L);
        order.setOrdType("4");
        order.setPrice(BigDecimal.valueOf(5000L));
        order.setSide("1");
        order.setTriggerPriceType("4");
        order.setTriggerPrice(BigDecimal.valueOf(5000L));
        order.setTriggerPriceType("2");
        order.setTriggerPriceDirection("D");
        return order;
    }

    @NotNull
    private static Billing getBilling() {
        Billing billing = new Billing();
        billing.setDaterange(Arrays.asList("2020-01-01","2020-02-01"));
        billing.setCurrencyId("");
        return billing;
    }

    @NotNull
    private static LedgerOrder getLedgerOrder() {
        LedgerOrder ledgerOrder = new LedgerOrder();
        ledgerOrder.setDaterange(Arrays.asList("2020-01-01","2020-03-13"));
        ledgerOrder.setOrderBookIndex("");
        return ledgerOrder;
    }

    @NotNull
    private static OrderCancel getOrderCancel() {
        OrderCancel orderCancel = new OrderCancel();
        orderCancel.setOrigClOrdID("202003110040");
        orderCancel.setClOrdID("202003110041");
        return orderCancel;
    }

    @NotNull
    private static OrderCancelReplace getOrderCancelReplace() {
        OrderCancelReplace orderCancelReplace = new OrderCancelReplace();
        orderCancelReplace.setOrigClOrdID("202003110030");
        orderCancelReplace.setClOrdID("202003110042");
        orderCancelReplace.setAccount("FIRM03001");
        orderCancelReplace.setMinQty(BigDecimal.valueOf(2L));
        orderCancelReplace.setOrdType("4");
        orderCancelReplace.setPrice(BigDecimal.valueOf(7000L));
        orderCancelReplace.setTriggerType("4");
        orderCancelReplace.setTriggerPrice(BigDecimal.valueOf(5000L));
        orderCancelReplace.setTriggerPriceType("2");
        orderCancelReplace.setTriggerPriceDirection("D");
        orderCancelReplace.setTimeInForce("6");
        orderCancelReplace.setExpireDate(20201212);
        return orderCancelReplace;
    }

    @NotNull
    private static OrderReport getOrderReport() {
        OrderReport orderReport = new OrderReport();
        orderReport.setClOrdID("202003110030");
        orderReport.setSenderCompId("");
        return orderReport;
    }

    @NotNull
    private static Statement getStatement() {
        Statement statement = new Statement();
        statement.setAccountIndex(2);
        statement.setTradeDate("2020-03");
        return statement;
    }
}
