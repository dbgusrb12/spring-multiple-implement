package com.hg.way8;

import com.hg.service.SampleService;
import com.hg.way8.SampleServiceFactory.SampleServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * way7 에서 구현체들의 관리를 따로 하는 객체를 생성해 위임한다.
 */
@Component
@RequiredArgsConstructor
public class Way8Runner implements ApplicationRunner {

    private final SampleServiceFactory sampleServiceFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sampleServicePrint(SampleServiceType.FIRST);
        sampleServicePrint(SampleServiceType.SECOND);
    }

    public void sampleServicePrint(SampleServiceType type) {
        SampleService sampleService = sampleServiceFactory.getSampleService(type);
        sampleService.print();
    }
}
