package com.mailstarter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * phuc.tranngoc created on 8/17/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    private String receiver;

    private String subject;

    private String staticContent;

    private List<String> attachmentPaths;

    private Map<String, Object> dynamicContents;

}
