package com.gruponach.pruebatecnica.services;

import java.util.Optional;

import com.gruponach.pruebatecnica.models.Jobs;
import com.gruponach.pruebatecnica.repository.JobsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsService {
    private JobsRepository jobsRepository;
    @Autowired
    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public Optional<Jobs> findJobById(Long id) {
        return jobsRepository.findById(id);
    }
    
}
