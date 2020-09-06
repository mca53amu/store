package com.store.retail.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.store.retail.entity.CustomerUser;
import com.store.retail.entity.Invoice;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerUser,Long>{

}
