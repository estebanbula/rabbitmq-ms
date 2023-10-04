package com.estebanbula.orders.dynamo.config;

import com.estebanbula.orders.model.Order;
import com.estebanbula.orders.model.SupplierOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderTableOps {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public void putOrders(SupplierOrder supplierOrder) {
        DynamoDbTable<SupplierOrder> orderDynamoDbTable = dynamoDbEnhancedClient.table("orders", TableSchema.fromBean(SupplierOrder.class));

        orderDynamoDbTable.putItem(supplierOrder);
    }

}
