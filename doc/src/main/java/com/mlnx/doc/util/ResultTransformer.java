package com.mlnx.doc.util;

import com.datastax.driver.core.Row;

public interface ResultTransformer<T> {

    T transform(Row row);
}
