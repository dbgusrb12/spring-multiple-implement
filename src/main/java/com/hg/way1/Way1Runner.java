package com.hg.way1;

import com.hg.service.FirstSampleServiceImpl;
import com.hg.service.SecondSampleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 모든 interface 의 구현체를 주입 받아서 분기 처리 하여 사용
 */
@Component
@RequiredArgsConstructor
public class Way1Runner implements ApplicationRunner {

    private final FirstSampleServiceImpl firstSampleService;
    private final SecondSampleServiceImpl secondSampleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        firstSampleServicePrint();
        secondSampleServicePrint();
    }

    private void firstSampleServicePrint() {
        firstSampleService.print();
    }

    private void secondSampleServicePrint() {
        secondSampleService.print();
    }
}
