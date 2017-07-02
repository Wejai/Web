package com.cbs.demo;

public class Account
{
  private String accountId;

  public String getAccountId() { return this.accountId; }

  public void setAccountId(String accountId) { this.accountId = accountId; }

  private String nickname;

  public String getNickname() { return this.nickname; }

  public void setNickname(String nickname) { this.nickname = nickname; }

  private Acc acc;

  public Acc getAcc() { return this.acc; }

  public void setAcc(Acc acc) { this.acc = acc; }

  private Servicer servicer;

  public Servicer getServicer() { return this.servicer; }

  public void setServicer(Servicer servicer) { this.servicer = servicer; }

  private String currency;

  public String getCurrency() { return this.currency; }

  public void setCurrency(String currency) { this.currency = currency; }
}
