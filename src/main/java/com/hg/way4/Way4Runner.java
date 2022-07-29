package com.hg.way4;

import com.hg.service.SampleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 같은 타입의 bean 을 List 로 주입 받을 수 있다.
 */
@Component
@RequiredArgsConstructor
public class Way4Runner implements ApplicationRunner {

    private final List<SampleService> sampleServices;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (SampleService sampleService : sampleServices) {
            sampleService.print();
        }
    }
}
