package com.kubilaykizilhan.bean;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class BeanConfig {
    public void firstBeforeBean(){
        log.info("Bean doğdu");
        System.out.println("Bean başlamadan önce ben çalıştım");
    }
    public void firstAfterBean(){
        log.info("Bean öldü");
        System.out.println("Bean bittikten sonra ben çalıştım");

    }
}
