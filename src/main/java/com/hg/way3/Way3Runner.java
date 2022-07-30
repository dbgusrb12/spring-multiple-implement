package com.hg.way3;

import com.hg.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 권장하지 않음 (지양)
 * application context 를 사용하여 bean 을 직접 가져와서 사용
 * application context 는 spring 이 관리하는 container 이기 때문에 비즈니스 로직에 포함되는 것은 권장하지 않는다.
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
