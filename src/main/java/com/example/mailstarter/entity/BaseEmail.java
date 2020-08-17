package com.example.mailstarter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trần Phúc created on 8/17/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEmail {

    private String receiver;

    private String subject;

    private String content;

}