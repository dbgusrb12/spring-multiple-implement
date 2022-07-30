package com.hg.service;

import org.springframework.stereotype.Service;

@Service
public class SecondSampleServiceImpl implements SampleService {

    @Override
    public void print() {
        System.out.println("SecondSampleServiceImpl print!");
    }

    @Override
    public void print(String type) {
        System.out.println(type + " SecondSampleServiceImpl print!");
    }
}
