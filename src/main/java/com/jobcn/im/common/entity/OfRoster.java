package com.jobcn.im.common.entity;

import javax.persistence.*;

@Entity
@Table(name="ofRoster")
public class OfRoster {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  /**
   *
   */
  private Long rosterId;
  private String username;
  private String jid;
  private Long sub;
  private Long ask;
  private Long recv;
  private String nick;

  public Long getRosterId() {
    return rosterId;
  }

  public void setRosterId(Long rosterId) {
    this.rosterId = rosterId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getJid() {
    return jid;
  }

  public void setJid(String jid) {
    this.jid = jid;
  }

  public Long getSub() {
    return sub;
  }

  public void setSub(Long sub) {
    this.sub = sub;
  }

  public Long getAsk() {
    return ask;
  }

  public void setAsk(Long ask) {
    this.ask = ask;
  }

  public Long getRecv() {
    return recv;
  }

  public void setRecv(Long recv) {
    this.recv = recv;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }
}
