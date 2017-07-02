package com.cbs.demo;

public class RootObject
{
  private String _rev;

  public String getRev() { return this._rev; }

  public void setRev(String _rev) { this._rev = _rev; }

  private String _id;

  public String getId() { return this._id; }

  public void setId(String _id) { this._id = _id; }
  
  private String aid;

  public String getAid() { return this.aid; }

  public void setAid(String aid) { this.aid = aid; }

  private String tid;

  public String getTid() { return this.tid; }

  public void setTid(String tid) { this.tid = tid; }

  private Links links;

  public Links getLinks() { return this.links; }

  public void setLinks(Links links) { this.links = links; }
  
  private String bid;

  public String getBid() { return this.bid; }

  public void setBid(String bid) { this.bid = bid; }

  private Data data;

  public Data getData() { return this.data; }

  public void setData(Data data) { this.data = data; }

  private Meta meta;

  public Meta getMeta() { return this.meta; }

  public void setMeta(Meta meta) { this.meta = meta; }
}