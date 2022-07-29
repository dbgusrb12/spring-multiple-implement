package com.hg.way6;

import com.hg.service.SampleService;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * way5 에서 bean name 을 enum 으로 관리하여 사용
 */
@Component
@RequiredArgsConstructor
public class Way6Runner implements ApplicationRunner {

    private final Map<String, SampleService> sampleServiceMap;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sampleServicePrint(SampleServiceType.FIRST);
        sampleServicePrint(SampleServiceType.SECOND);
    }

    private void sampleServicePrint(SampleServiceType type) {
        SampleService sampleService = sampleServiceMap.get(type.getBeanName());
        sampleService.print();
    }

    @AllArgsConstructor
    @Getter
    private enum SampleServiceType {
        FIRST("firstSampleServiceImpl"),
        SECOND("secondSampleServiceImpl");

        private String beanName;
    }
}
