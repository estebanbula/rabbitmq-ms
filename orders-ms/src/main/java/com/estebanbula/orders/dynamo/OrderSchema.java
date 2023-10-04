package com.estebanbula.orders.dynamo;

import com.estebanbula.orders.model.Order;
import com.estebanbula.orders.model.SupplierOrder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;
import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primarySortKey;

public class OrderSchema {

    public static final StaticTableSchema<Order> ORDER_STATIC_TABLE_SCHEMA =
            StaticTableSchema.builder(Order.class)
                    .addAttribute(String.class, id -> id.name("orderId")
                            .getter(Order::getOrderId)
                            .setter(Order::setOrderId)
                            .tags(primaryPartitionKey()))
                    .addAttribute(String.class, name -> name.name("name")
                            .getter(Order::getName)
                            .setter(Order::setName)
                            .tags(primarySortKey()))
                    .addAttribute(Integer.class, qty -> qty.name("quantity")
                            .getter(Order::getQuantity)
                            .setter(Order::setQuantity))
                    .addAttribute(Double.class, price -> price.name("price")
                            .getter(Order::getPrice)
                            .setter(Order::setPrice))
                    .build();

    public static final StaticTableSchema<SupplierOrder> SUPPLIER_ORDER_STATIC_TABLE_SCHEMA =
            StaticTableSchema.builder(SupplierOrder.class)
                    .addAttribute(String.class, sName -> sName.name("supplierName")
                            .getter(SupplierOrder::getSupplierName)
                            .setter(SupplierOrder::setSupplierName))
                    .addAttribute(Integer.class, iPrice -> iPrice.name("ingressPrice")
                            .getter(SupplierOrder::getIngressPrice)
                            .setter(SupplierOrder::setIngressPrice))
                    .build();
}
