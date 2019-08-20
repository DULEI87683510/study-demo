package com.dl.study.aysnc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * 异步处理请求demo
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {
    /**
     * tomcat主线程执行处理任务
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/helloAsync")
    public String helloAsync() throws InterruptedException {
      log.info("主线程开始执行helloAsync任务..........");
      Thread.sleep(100);
      log.info("主线程开始执行helloAsync任务结束..........");

        return "success";
    }

    /**
     * tomcat主线程负责接受请求，请求的处理交给其它线程
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/helloAsyncCallable")
    public Callable<String> helloAsyncCallable() throws InterruptedException {
        log.info("主线程接收到请求..........");
        Callable<String> callable=()->{
            log.info("副线程开始执行helloAsync任务..........");
            Thread.sleep(100);
            log.info("副线程开始执行helloAsync任务结束..........");
            return "success";
        };
        log.info("主线程返回请求..........");
        return callable;
    }

}
