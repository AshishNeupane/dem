package com.ann.dem.dto.search;

import lombok.Data;

import java.util.List;

@Data
public class SortDTO {
    private String field;
    private String orderType;
}
