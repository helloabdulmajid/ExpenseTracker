//package com.abdulmajid.expensetracker.model;
//
//import jakarta.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name="incomes")
//public class Income {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private Integer amount;
//    private String source;
//    private String paymentMode;
//    private String note;
//    private Date createdAt ;
//    private Date updatedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//}
