package com.innovatech.resource_service.init;

import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.innovatech.resource_service.repository.ResourceRepository;
import com.innovatech.resource_service.model.entity.Resource;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {
    
    @Autowired
    private ResourceRepository resourceRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoadDataBase.class);
    

    @Override
    public void run (String... args) throws Exception {
        Faker faker = new Faker();
        if (resourceRepository.count()==0){
            for (int i = 0; i<100; i++){
                Resource resource = new Resource();
                resource.setName(faker.name().fullName());
                resource.setType(faker.options().option("Human", "Material", "Financial"));
                resource.setProjectId(faker.number().numberBetween(1L, 100L));
                resource.setUtilizationPercentage(faker.number().randomDouble(2, 0, 100));
                resource.setStatus(faker.options().option("Available", "In Use", "Maintenance"));
                resource.setCost(faker.number().randomDouble(2, 100, 10000));
                resource.setProjectId(faker.number().numberBetween(1L, 100L)); // Asumiendo que hay proyectos con IDs del 1 al 100

                resource = resourceRepository.save(resource);
                logger.info("Resource created: " + resource.toString());
            }
        }
    }
}
