package com.smallAttr.github;

import com.smallAttr.github.domain.Customer;
import com.smallAttr.github.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: chenGang
 * Date: 2018/9/25 下午5:52
 *
 * 构建SpringBoot项目时，我们通常有一些预先数据的加载
 */
@Component
public class CommandLineCrudRunner implements CommandLineRunner {

  public static final Logger logger = LoggerFactory.getLogger(CommandLineCrudRunner.class);

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public void run(String... strings) throws Exception {
    customerRepository.save(new Customer("John", "Doe"));
    customerRepository.save(new Customer("Jennifer", "Wilson"));

    logger.info("Customers found with findAll():");
    customerRepository.findAll().forEach(customer -> logger.info(customer.toString()));

    logger.info("Customer found with findById(1L)");
    Customer customer = customerRepository.findOne(1L);
    logger.info(customer.toString());


    logger.info("Customer found with findByLastName('Wilson')");
    customerRepository.findByLastName("Wilson").forEach(item -> logger.info(item.toString()));

  }
}
