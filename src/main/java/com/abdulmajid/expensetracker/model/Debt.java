//package com.abdulmajid.expensetracker.model;
//
//import jakarta.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name="debts")
//public class Debt {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private Integer amount;
//    private String creditor;
//    private String note;
//    private Date createdAt ;
//    private Date updatedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//}
