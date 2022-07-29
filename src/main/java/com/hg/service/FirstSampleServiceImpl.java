package com.hg.service;

import org.springframework.stereotype.Service;

@Service
public class FirstSampleServiceImpl implements SampleService {

    @Override
    public void print() {
        System.out.println("FirstSampleServiceImpl print!");
    }
}
