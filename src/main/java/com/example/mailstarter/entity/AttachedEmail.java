package com.example.mailstarter.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Trần Phúc created on 8/17/2020
 */
public class AttachedEmail extends BaseEmail {

    @Getter
    @Setter
    private String[] attachmentPaths;

}
