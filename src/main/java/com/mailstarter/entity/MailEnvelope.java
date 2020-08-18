package com.mailstarter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * phuc.tranngoc created on 8/17/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailEnvelope {

    private String receiver;

    private String subject;

    private String content;

    private List<String> attachmentPaths;

}
