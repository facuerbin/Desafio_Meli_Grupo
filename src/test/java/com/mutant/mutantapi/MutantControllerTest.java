package com.mutant.mutantapi;

import com.mutant.mutantapi.controller.MutantController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class MutantControllerTest {

    @Autowired
    MutantController mutantController;

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(mutantController).isNotNull();
    }

}
