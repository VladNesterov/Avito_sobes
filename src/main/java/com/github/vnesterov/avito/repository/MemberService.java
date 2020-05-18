package com.github.vnesterov.avito.repository;

import java.util.List;

public interface MemberService {
    void add(String meeting, String name);

    void delete(String meeting, List<String> name);
}
