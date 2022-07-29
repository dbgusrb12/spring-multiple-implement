package com.hg.way7;

import com.hg.service.FirstSampleServiceImpl;
import com.hg.service.SampleService;
import com.hg.service.SecondSampleServiceImpl;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * way6 에서 bean name 이 string 문자열인게 type safety 하지 않다고 생각해 class 기반으로 검색 할 수 있게 수정
 */
@Component
public class Way7Runner implements ApplicationRunner {

    private Map<SampleServiceType, SampleService> sampleServiceMap;

    public Way7Runner(List<SampleService> sampleServices) {
        sampleServiceMap = new HashMap<>();
        for (SampleService sampleService : sampleServices) {
            SampleServiceType sampleServiceType = SampleServiceType.typeOf(
                sampleService.getClass());
            sampleServiceMap.put(sampleServiceType, sampleService);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sampleServicePrint(SampleServiceType.FIRST);
        sampleServicePrint(SampleServiceType.SECOND);
    }

    private void sampleServicePrint(SampleServiceType type) {
        SampleService sampleService = sampleServiceMap.get(type);
        sampleService.print();
    }

    private enum SampleServiceType {
        FIRST(FirstSampleServiceImpl.class),
        SECOND(SecondSampleServiceImpl.class);

        Class<? extends SampleService> clazz;
        private static final Map<Class<? extends SampleService>, SampleServiceType> findByType =
            Collections.unmodifiableMap(Stream.of(values())
                .collect(Collectors.toMap(SampleServiceType::getClazz, Function.identity())));

        SampleServiceType(Class<? extends SampleService> clazz) {
            this.clazz = clazz;
        }

        public static SampleServiceType typeOf(Class<? extends SampleService> clazz) {
            return Optional.ofNullable(findByType.get(clazz)).orElseThrow(RuntimeException::new);
        }

        public Class<? extends SampleService> getClazz() {
            return clazz;
        }
    }
}
