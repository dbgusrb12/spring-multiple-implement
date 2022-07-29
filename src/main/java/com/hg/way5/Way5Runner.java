package com.hg.way5;

import com.hg.service.SampleService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 같은 타입의 bean 을 Map 로 주입 받을 수 있다.
 * Map 의 key 는 bean name 이다.
 */
@Component
@RequiredArgsConstructor
public class Way5Runner implements ApplicationRunner {

    private final Map<String, SampleService> sampleServiceMap;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sampleServicePrint("firstSampleServiceImpl");
        sampleServicePrint("secondSampleServiceImpl");
    }

    private void sampleServicePrint(String beanName) {
        SampleService sampleService = sampleServiceMap.get(beanName);
        sampleService.print();
    }
}
