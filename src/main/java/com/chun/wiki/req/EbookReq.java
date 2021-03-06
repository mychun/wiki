package com.chun.wiki.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class EbookReq extends PageReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}
