package com.jiangj.event.vo;


import java.io.Serializable;

/**
 * @author Jiang Jian
 * @since 2018/10/8
 */
public class EventContent implements Serializable {

    private String detail;

    private String sellerNo;

    private String tenantCode;

    private String originatorNo;

    private String customerNo;

    public EventContent(String detail, String sellerNo, String tenantCode, String originatorNo, String customerNo) {
        this.detail = detail;
        this.sellerNo = sellerNo;
        this.tenantCode = tenantCode;
        this.originatorNo = originatorNo;
        this.customerNo = customerNo;
    }

    public EventContent() {
        super();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getOriginatorNo() {
        return originatorNo;
    }

    public void setOriginatorNo(String originatorNo) {
        this.originatorNo = originatorNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }


}
