package com.basma.PaymentMicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetTime;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "paid_at", nullable = false)
    private OffsetTime paidAt;

    @Column(name = "order_ref", nullable = false)
    private String orderRef;

    @Column(name = "payment_details")
    private String paymentDetails;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "payment_total", nullable = false)
    private double total;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("payment_details")
    private User user;

    public Payment() {
    }

    public Payment(Long paymentId, OffsetTime paidAt, String orderRef, String paymentDetails, String paymentType, double total, User user) {
        this.paymentId = paymentId;
        this.paidAt = paidAt;
        this.orderRef = orderRef;
        this.paymentDetails = paymentDetails;
        this.paymentType = paymentType;
        this.total = total;
        this.user = user;
    }

    public Payment(OffsetTime paidAt, String orderRef, String paymentDetails, String paymentType, double total, User user) {
        this.paidAt = paidAt;
        this.orderRef = orderRef;
        this.paymentDetails = paymentDetails;
        this.paymentType = paymentType;
        this.total = total;
        this.user = user;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public OffsetTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(OffsetTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
