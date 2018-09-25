package com.smallAttr.github.repository;

import com.smallAttr.github.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author: chenGang
 * Date: 2018/9/25 下午5:41
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  List<Customer> findByLastName(String lastName);

}
