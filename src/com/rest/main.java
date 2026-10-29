package com.rest;

import com.rest.cli.menuitemcli;
import com.rest.cli.orderscli;
import com.rest.cli.restaurantcli;
import com.rest.cli.tablecli;
import com.rest.cli.paymentcli;
import com.rest.respository.paymentrepository;
import com.rest.respository.billrepository;
import com.rest.respository.menurepository;
import com.rest.respository.ordersrepository;
import com.rest.respository.tablerespository;
import com.rest.services.menuservice;
import com.rest.services.orderservice;
import com.rest.services.paymentservice;
import com.rest.services.billservice;
import com.rest.services.tableservice;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        tablerespository tableRepository = new tablerespository();
        menurepository menuRepository = new menurepository();
        ordersrepository orderRepository = new ordersrepository();
        paymentrepository paymentRepository = new paymentrepository();
        billrepository billRepository = new billrepository();

        tableservice tableService = new tableservice(tableRepository);
        menuservice menuService = new menuservice(menuRepository);
        orderservice orderService = new orderservice(orderRepository, menuService);
        paymentservice paymentService = new paymentservice(paymentRepository);
        billservice billService = new billservice(billRepository);

        Scanner scanner = new Scanner(System.in);
        tablecli tableCli = new tablecli(tableService);
        menuitemcli menuCli = new menuitemcli(menuService, scanner);
        paymentcli paymentCli = new paymentcli(paymentService, billService, scanner);
        orderscli orderCli = new orderscli(tableService, orderService, menuCli, paymentCli, scanner);
        restaurantcli cli = new restaurantcli(tableCli, menuCli, orderCli, scanner);
        cli.start();
    }
}
