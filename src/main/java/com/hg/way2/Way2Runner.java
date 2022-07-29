package com.hg.way2;

import com.hg.service.FirstSampleServiceImpl;
import com.hg.service.SampleService;
import com.hg.service.SecondSampleServiceImpl;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 인터페이스의 구현체들을 Map 에 넣어두고 사용
 */
@Component
@RequiredArgsConstructor
public class Way2Runner implements ApplicationRunner {

    private final FirstSampleServiceImpl firstSampleService;
    private final SecondSampleServiceImpl secondSampleService;
    private Map<String, SampleService> sampleServiceMap;

    @PostConstruct
    public void init() {
        sampleServiceMap = new HashMap<>();
        sampleServiceMap.put("first", firstSampleService);
        sampleServiceMap.put("second", secondSampleService);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sampleServicePrint("first");
        sampleServicePrint("second");
    }

    private void sampleServicePrint(String type) {
        sampleServiceMap.get(type).print();
    }
}
