package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.ApplicationContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Marek Turis
 */
@Configuration
@ComponentScan({"cz.muni.fi.pa165.service.services", "cz.muni.fi.pa165.service.facadeImpl", "cz.muni.fi.pa165.service.exceptions"})
@Import(ApplicationContextConfiguration.class)
public class ServiceConfig {
}
