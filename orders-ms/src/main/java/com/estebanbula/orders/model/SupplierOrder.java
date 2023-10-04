package com.estebanbula.orders.model;

import lombok.EqualsAndHashCode;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
@EqualsAndHashCode(callSuper = true)
public class SupplierOrder extends Order {

    private String supplierName;
    private Integer ingressPrice;

    public SupplierOrder() {}

    public SupplierOrder(String supplierName, Integer ingressPrice) {
        this.supplierName = supplierName;
        this.ingressPrice = ingressPrice;
    }

    public SupplierOrder(String orderId, String name, Integer quantity, Double price, String supplierName, Integer ingressPrice) {
        super(orderId, name, quantity, price);
        this.supplierName = supplierName;
        this.ingressPrice = ingressPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getIngressPrice() {
        return ingressPrice;
    }

    public void setIngressPrice(Integer ingressPrice) {
        this.ingressPrice = ingressPrice;
    }
}
