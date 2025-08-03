package com.tims.common.core.domin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class ResultTest {
    @Test
    public void testResult() {
        Result<String> result = Result.success("成功");
        System.out.println(result);
    }
}
