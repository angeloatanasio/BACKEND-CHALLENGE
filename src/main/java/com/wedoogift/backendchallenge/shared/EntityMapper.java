package com.wedoogift.backendchallenge.shared;

import java.util.List;

public interface EntityMapper <D, E> {

    D toDto(E entity);
}
