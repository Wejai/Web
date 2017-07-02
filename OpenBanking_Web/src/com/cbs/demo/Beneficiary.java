package com.cbs.demo;

public class Beneficiary
{
  private String accountId;

  public String getAccountId() { return this.accountId; }

  public void setAccountId(String accountId) { this.accountId = accountId; }

  private Servicer servicer;

  public Servicer getServicer() { return this.servicer; }

  public void setServicer(Servicer servicer) { this.servicer = servicer; }

  private CreditorAccount creditorAccount;

  public CreditorAccount getCreditorAccount() { return this.creditorAccount; }

  public void setCreditorAccount(CreditorAccount creditorAccount) { this.creditorAccount = creditorAccount; }

  private String beneficiaryId;

  public String getBeneficiaryId() { return this.beneficiaryId; }

  public void setBeneficiaryId(String beneficiaryId) { this.beneficiaryId = beneficiaryId; }

  private String reference;

  public String getReference() { return this.reference; }

  public void setReference(String reference) { this.reference = reference; }
}