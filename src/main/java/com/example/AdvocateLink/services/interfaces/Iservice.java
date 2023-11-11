package com.example.AdvocateLink.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface Iservice<T> {
    T insert(T t);

    void deleteById(Long id);

    T update(Long id, T t);

    Page<T> list(PageRequest pageRequest);

    T findById(Long id);
}
