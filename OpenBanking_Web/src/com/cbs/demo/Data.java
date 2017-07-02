package com.cbs.demo;

public class Data
{
  private Transaction transaction;

  public Transaction getTransaction() { return this.transaction; }

  public void setTransaction(Transaction transaction) { this.transaction = transaction; }

  private Beneficiary beneficiary;

  public Beneficiary getBeneficiary() { return this.beneficiary; }

  public void setBeneficiary(Beneficiary beneficiary) { this.beneficiary = beneficiary; }

  private Account account;

  public Account getAccount() { return this.account; }

  public void setAccount(Account account) { this.account = account; }
}