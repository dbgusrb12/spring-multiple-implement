package com.hg.way3;

import com.hg.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * application context 를 사용하여 bean 을 직접 가져와서 사용
 */
@Component
@RequiredArgsConstructor
public class Way3Runner implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sampleServicePrint("firstSampleServiceImpl");
        sampleServicePrint("secondSampleServiceImpl");
    }

    private void sampleServicePrint(String beanName) {
        SampleService sampleService = applicationContext.getBean(beanName, SampleService.class);
        sampleService.print();
    }
}
