package com.innovatech.project_service.init;

import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

import com.innovatech.project_service.model.entity.Project;
import com.innovatech.project_service.repository.ProjectRepository;


@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {
    
    @Autowired
    private ProjectRepository projectRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoadDataBase.class);


    @Override
    public void run (String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));
        if (projectRepository.count()==0){
            for (int i = 0; i<100; i++){
                Project project = new Project();
                project.setName(faker.app().name());
                project.setDescription(faker.lorem().sentence());
                project.setProjectIndustry(faker.company().industry());
                project.setStartDate(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
                project.setBudget(faker.number().randomDouble(2, 1000, 100000));
                project.setEndDate(project.getStartDate().plusDays(faker.number().numberBetween(30, 365)));
                project.setStatus(faker.options().option("Active", "Completed", "On Hold"));

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);
                project.setClientRun(restante+"-"+ultimo);

                project = projectRepository.save(project);
                logger.info("Project created: " + project.toString());
            }
        }
    }
    
}
