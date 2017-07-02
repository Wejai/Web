package com.cbs.demo;

public class Balance
{
  private String creditDebitIndicator;

  public String getCreditDebitIndicator() { return this.creditDebitIndicator; }

  public void setCreditDebitIndicator(String creditDebitIndicator) { this.creditDebitIndicator = creditDebitIndicator; }

  private Amount amount;

  public Amount getAmount() { return this.amount; }

  public void setAmount(Amount amount) { this.amount = amount; }

  private String type;

  public String getType() { return this.type; }

  public void setType(String type) { this.type = type; }
}
