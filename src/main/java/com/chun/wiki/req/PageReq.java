package com.chun.wiki.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PageReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page;
    private int size;
}
