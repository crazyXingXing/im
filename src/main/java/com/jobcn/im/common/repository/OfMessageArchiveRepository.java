package com.jobcn.im.common.repository;

import com.jobcn.im.common.entity.OfMessageArchive;
import com.jobcn.im.common.entity.OfRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfMessageArchiveRepository extends JpaRepository<OfMessageArchive, Long> {
    List<OfMessageArchive> findByFromJIDOrAndToJIDOrderBySentDate(String fromJID,String toJID);
}
