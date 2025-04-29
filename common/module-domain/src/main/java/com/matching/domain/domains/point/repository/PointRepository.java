package com.matching.domain.domains.point.repository;

import com.matching.storage.database.model.point.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    // 전체 조회
    Page<Point> findAll(Pageable pageable);
}