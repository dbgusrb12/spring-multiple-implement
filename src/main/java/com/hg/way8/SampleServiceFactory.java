package com.hg.way8;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleServiceFactory {

    private Map<SampleServiceType, SampleService> sampleServiceMap;

    public SampleServiceFactory(List<SampleService> sampleServices) {
        sampleServiceMap = new HashMap<>();
        for (SampleService sampleService : sampleServices) {
            try {
                SampleServiceType sampleServiceType = SampleServiceType.typeOf(
                    sampleService.getClass());
                sampleServiceMap.put(sampleServiceType, sampleService);
            } catch (RuntimeException e) {
                log.error("MainSampleServiceImpl is not defined ==> {}", e.getMessage());
            }
        }
    }

    public SampleService getSampleService(SampleServiceType type) {
        return sampleServiceMap.get(type);
    }

    protected enum SampleServiceType {
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
