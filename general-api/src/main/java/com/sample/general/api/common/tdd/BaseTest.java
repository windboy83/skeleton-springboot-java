package com.sample.general.api.common.tdd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.transaction.annotation.Transactional;

@EnabledIf(expression = "#{environment.acceptsProfiles('local', 'dev', 'test', 'prod')}", loadContext = true)
@SpringBootTest
@Transactional
@ActiveProfiles(profiles = {"local"})
public abstract class BaseTest {

}
