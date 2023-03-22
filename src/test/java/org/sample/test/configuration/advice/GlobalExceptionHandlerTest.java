package org.sample.test.configuration.advice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sample.test.assets.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ExceptionTestController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void globalExceptionHandlerTest_exception() throws Exception {
        // given
        final ErrorCode expectedErrorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        final String expectedErrorMessage = "test-exception";

        // when
        mockMvc.perform(get("/test-exception"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error.code", Matchers.is(expectedErrorCode.getCode())))
                .andExpect(jsonPath("$.error.name", Matchers.is(expectedErrorCode.name())))
                .andExpect(jsonPath("$.error.message", Matchers.is(expectedErrorMessage)));

        // then

    }

}
