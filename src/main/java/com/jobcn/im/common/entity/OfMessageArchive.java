package com.jobcn.im.common.entity;


import javax.persistence.*;

@Entity
@Table(name="ofMessageArchive")
public class OfMessageArchive {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long messageId;
  private Long conversationId;
  private String fromJID;
  private String fromJIDResource;
  private String toJID;
  private String toJIDResource;
  private Long sentDate;
  private String stanza;
  private String body;

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  public Long getConversationId() {
    return conversationId;
  }

  public void setConversationId(Long conversationId) {
    this.conversationId = conversationId;
  }

  public String getFromJID() {
    return fromJID;
  }

  public void setFromJID(String fromJID) {
    this.fromJID = fromJID;
  }

  public String getFromJIDResource() {
    return fromJIDResource;
  }

  public void setFromJIDResource(String fromJIDResource) {
    this.fromJIDResource = fromJIDResource;
  }

  public String getToJID() {
    return toJID;
  }

  public void setToJID(String toJID) {
    this.toJID = toJID;
  }

  public String getToJIDResource() {
    return toJIDResource;
  }

  public void setToJIDResource(String toJIDResource) {
    this.toJIDResource = toJIDResource;
  }

  public Long getSentDate() {
    return sentDate;
  }

  public void setSentDate(Long sentDate) {
    this.sentDate = sentDate;
  }

  public String getStanza() {
    return stanza;
  }

  public void setStanza(String stanza) {
    this.stanza = stanza;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
