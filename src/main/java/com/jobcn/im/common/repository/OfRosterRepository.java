package com.jobcn.im.common.repository;

import com.jobcn.im.common.entity.OfRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfRosterRepository extends JpaRepository<OfRoster, Long> {

}
