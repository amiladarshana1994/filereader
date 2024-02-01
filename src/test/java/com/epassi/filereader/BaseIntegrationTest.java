package com.epassi.filereader;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FileReaderApplication.class)
@WebAppConfiguration
public abstract class BaseIntegrationTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SneakyThrows
    protected String readFile(String responseFile) {
        return IOUtils.toString(new ClassPathResource(responseFile).getInputStream(), StandardCharsets.UTF_8);
    }
}
