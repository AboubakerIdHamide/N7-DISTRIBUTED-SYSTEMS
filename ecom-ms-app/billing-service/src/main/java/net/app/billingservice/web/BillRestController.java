package net.app.billingservice.web;

import net.app.billingservice.entities.Bill;
import net.app.billingservice.entities.ProductItem;
import net.app.billingservice.feign.CustomerServiceRestClient;
import net.app.billingservice.feign.InventoryServiceRestClient;
import net.app.billingservice.repository.BillRepository;
import net.app.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;

    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryServiceRestClient.getProduct(productItem.getProductId()));
        });
        return bill;
    }
}
