package com.smallAttr.github.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: chenGang
 * Date: 2018/9/25 下午5:39
 */
@Entity
@NoArgsConstructor
public class Customer {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
