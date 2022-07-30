package com.hg.way9;

import com.hg.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 해당 interface 가 구현체가 여러개라도 사용하는 부분에서는 bean 의 내부 구현이 어떻게 되있는지 알 필요가 없기 때문에
 * 구현체들의 관리를 따로 하는 구현체를 만들어 위임한다.
 * 물론 타입을 구분해야 하는 파라미터가 필요하지만, 해당 사항은 interface 를 설계 할 때 필요한 부분이다.
 */
@Component
@RequiredArgsConstructor
public class Way9Runner implements ApplicationRunner {

    private final SampleService sampleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // client 단에서는 요청에 대한 응답만 받으면 된다.
        // 해당 interface 의 구현체가 뭐뭐 있고, 어떻게 구현되어 있고... 등등을 신경 쓸 필요 X
        sampleService.print("FIRST");
        sampleService.print("SECOND");
    }
}
