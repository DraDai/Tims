package com.teacher.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 定义了一个名为 PageBean 的泛型类，它通常用于封装分页查询的结果。这个类包含了两个主要属性：total 和 rows。
 * <br>以下是每个属性的详细解释和类的使用场景：
 * <br><h4>total:</h4>
 * &nbsp&nbsp&nbsp&nbsp类型为 long，表示查询结果的总记录数。这个数字通常用于分页显示，以便知道总共有多少条数据，从而计算出需要多少页。
 * <br><h4>rows:</h4>
 * &nbsp&nbsp&nbsp&nbsp类型为 List<T>，表示当前页的数据列表。T 是一个泛型参数，允许 PageBean 承载任何类型的数据。
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private long total;
    private List<T> rows;
}
