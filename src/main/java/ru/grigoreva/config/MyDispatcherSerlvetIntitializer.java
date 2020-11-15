package ru.grigoreva.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Используется для конфигурации вместо web.xml ( При запуске Tomcat считывает web.xml и использует считанную конфигурацию)
public class MyDispatcherSerlvetIntitializer{
        //extends AbstractAnnotationConfigDispatcherServletInitializer {
/*
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /*содержит  преобразователь представления, сопоставления обработчиков, контроллеры*/
 /*   @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfiguration.class};
    }

    @Override//Конфигурация загружается из класса MyConfiguration
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }  // указывает путь для запросов пользователя

*/}
