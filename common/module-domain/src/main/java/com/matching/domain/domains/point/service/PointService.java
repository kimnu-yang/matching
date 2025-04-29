package com.matching.domain.domains.point.service;

import com.matching.domain.domains.point.repository.PointRepository;
import com.matching.storage.database.model.point.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    public Point findByIdx(Long idx) {
        return pointRepository.findById(idx).orElse(null);
    }

    public List<Point> findAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        return pointRepository.findAll(pageable).getContent();
    }

    public Point save(Point point) { return pointRepository.save(point); }
}
