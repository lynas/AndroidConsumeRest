package com.lynas.androidresttest.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LynAs on 22-Mar-16
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public @Data
class Book {

    private Long id;
    private String name;
    private Organization organization;

}
