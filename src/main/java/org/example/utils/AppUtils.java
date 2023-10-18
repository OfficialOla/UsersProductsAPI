package org.example.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class AppUtils {

    public static Pageable buildPageRequest(int page, int items) {
    if (page<= 0) page =1;
    if (items <= 0)items =10;
    page -=1;
    return PageRequest.of(page, items);
    }
}
