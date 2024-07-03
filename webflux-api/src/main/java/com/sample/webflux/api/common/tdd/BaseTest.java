package com.sample.webflux.api.common.tdd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;

@EnabledIf(expression = "#{environment.acceptsProfiles('local', 'dev', 'test', 'prod')}", loadContext = true)
@SpringBootTest
@ActiveProfiles(profiles = {"local"})
public abstract class BaseTest {

}
