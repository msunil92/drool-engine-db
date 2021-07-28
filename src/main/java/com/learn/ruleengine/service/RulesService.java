package com.learn.ruleengine.service;

import com.learn.ruleengine.model.RulesEntity;
import com.learn.ruleengine.repository.RuleEntityRepository;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;


/**
 * Created by neo on 17/7/31.
 */

@Service
public class RulesService {

    public static KieContainer kieContainer;

    @Autowired
    private RuleEntityRepository ruleEntityRepository;

    public void saverules(String rules) {
        RulesEntity rulesEntity = new RulesEntity();
        rulesEntity.setRuleKey("rule1");
        rulesEntity.setContent(rules);
        ruleEntityRepository.save(rulesEntity);
    }

    public void deleteRule(Long id) {
        ruleEntityRepository.deleteById(id);
    }

    public void save() throws Exception{

        String fileName = "rules/rules.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);


        RulesEntity rulesEntity = new RulesEntity();
        rulesEntity.setRuleKey("rule1");
        rulesEntity.setContent(content);
        ruleEntityRepository.save(rulesEntity);
    }

    public  void reload(){
        KieContainer kieContainer=loadContainerFromString(loadRules());
        this.kieContainer=kieContainer;
    }

    public  KieContainer getKieContainer(){
        return this.kieContainer;
    }

    private List<RulesEntity> loadRules(){
        List<RulesEntity> rulesEntities = ruleEntityRepository.findAll();
        System.out.println("Data"+ rulesEntities.toString());
        return rulesEntities;
    }

    private  KieContainer loadContainerFromString(List<RulesEntity> rulesEntities) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (RulesEntity rulesEntity : rulesEntities) {
            String  drl= rulesEntity.getContent();
            kfs.write("src/main/resources/" + drl.hashCode() + ".drl", drl);

            System.out.println("src/main/resources/" + drl.hashCode() + ".drl" + drl);
        }

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build rules : " + (endTime - startTime)  + " ms" );
        startTime = System.currentTimeMillis();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        System.out.println("Time to load container: " + (endTime - startTime)  + " ms" );
        return kContainer;
    }

}
