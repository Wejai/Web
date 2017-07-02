package com.cbs.demo;

import java.util.Date;

public class Transaction
{
  private String status;

  public String getStatus() { return this.status; }

  public void setStatus(String status) { this.status = status; }

  private String creditDebitIndicator;

  public String getCreditDebitIndicator() { return this.creditDebitIndicator; }

  public void setCreditDebitIndicator(String creditDebitIndicator) { this.creditDebitIndicator = creditDebitIndicator; }

  private Balance balance;

  public Balance getBalance() { return this.balance; }

  public void setBalance(Balance balance) { this.balance = balance; }

  private Date bookingDateTime;

  public Date getBookingDateTime() { return this.bookingDateTime; }

  public void setBookingDateTime(Date bookingDateTime) { this.bookingDateTime = bookingDateTime; }

  private String transactionReference;

  public String getTransactionReference() { return this.transactionReference; }

  public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }

  private String accountId;

  public String getAccountId() { return this.accountId; }

  public void setAccountId(String accountId) { this.accountId = accountId; }

  private String transactionId;

  public String getTransactionId() { return this.transactionId; }

  public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

  private Amount2 amount;

  public Amount2 getAmount() { return this.amount; }

  public void setAmount(Amount2 amount) { this.amount = amount; }

  private BankTransactionCode bankTransactionCode;

  public BankTransactionCode getBankTransactionCode() { return this.bankTransactionCode; }

  public void setBankTransactionCode(BankTransactionCode bankTransactionCode) { this.bankTransactionCode = bankTransactionCode; }

  private String transactionInformation;

  public String getTransactionInformation() { return this.transactionInformation; }

  public void setTransactionInformation(String transactionInformation) { this.transactionInformation = transactionInformation; }

  private Date valueDateTime;

  public Date getValueDateTime() { return this.valueDateTime; }

  public void setValueDateTime(Date valueDateTime) { this.valueDateTime = valueDateTime; }

  private ProprietaryBankTransactionCode proprietaryBankTransactionCode;

  public ProprietaryBankTransactionCode getProprietaryBankTransactionCode() { return this.proprietaryBankTransactionCode; }

  public void setProprietaryBankTransactionCode(ProprietaryBankTransactionCode proprietaryBankTransactionCode) { this.proprietaryBankTransactionCode = proprietaryBankTransactionCode; }
}