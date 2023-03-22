package org.sample.test.configuration.advice;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("local")
@RestController
public class ExceptionTestController {

    @GetMapping("/test-exception")
    public void testException() throws Exception {
        throw new Exception("test-exception");
    }


}
