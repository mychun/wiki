package com.chun.wiki.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class PageResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long total;
    private List<T> list;
}
