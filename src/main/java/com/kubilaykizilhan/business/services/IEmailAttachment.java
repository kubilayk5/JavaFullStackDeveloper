package com.kubilaykizilhan.business.services;

import com.kubilaykizilhan.business.dto.EmailDto;

public interface IEmailAttachment  {

  public EmailDto basicSendEmail(EmailDto emailDto);
  public EmailDto intermediaSendEmail(EmailDto emailDto);
}