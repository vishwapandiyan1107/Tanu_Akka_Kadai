package com.rest.respository;

import com.rest.models.payment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class paymentrepository {
    private final List<payment> payments = new ArrayList<>();

    public void save(payment value) {
        payments.add(value);
    }

    public List<payment> getAll() {
        return Collections.unmodifiableList(payments);
    }

    public List<payment> findAll() {
        return new ArrayList<>(payments);
    }

    public payment findById(int id){
        for (payment p : payments) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    }



