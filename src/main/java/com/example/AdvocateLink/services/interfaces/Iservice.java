package com.example.AdvocateLink.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface Iservice<T> {
    @Transactional
    T insert(T t);
    @Transactional
    void deleteById(Long id);
    @Transactional
    T update(Long id, T t);
    @Transactional(readOnly = true)
    Page<T> list(Pageable pageable);
    @Transactional(readOnly = true)
    T findById(Long id);
}
