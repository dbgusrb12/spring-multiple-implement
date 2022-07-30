package com.hg.way9;

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
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MainSampleServiceImpl implements SampleService {

    private Map<SampleServiceType, SampleService> sampleServiceMap;

    public MainSampleServiceImpl(List<SampleService> sampleServices) {
        sampleServiceMap = new HashMap<>();
        for (SampleService sampleService : sampleServices) {
            SampleServiceType sampleServiceType = SampleServiceType.typeOf(
                sampleService.getClass());
            sampleServiceMap.put(sampleServiceType, sampleService);
        }
    }

    @Override
    public void print() {
        System.out.println("MainSampleServiceImpl print!");
    }

    @Override
    public void print(String type) {
        SampleServiceType sampleServiceType = SampleServiceType.valueOf(type);
        SampleService sampleService = sampleServiceMap.get(sampleServiceType);
        sampleService.print(type);
    }

    protected enum SampleServiceType {
        FIRST(FirstSampleServiceImpl.class),
        SECOND(SecondSampleServiceImpl.class);

        Class<? extends SampleService> clazz;
        private static final Map<Class<? extends SampleService>, SampleServiceType> findByType =
            Collections.unmodifiableMap(Stream.of(values())
                .collect(
                    Collectors.toMap(SampleServiceType::getClazz, Function.identity())));

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
