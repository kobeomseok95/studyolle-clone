package com.clone.studyolle.study;

import com.clone.studyolle.tag.Tag;
import com.clone.studyolle.zone.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface StudyRepositoryExtension {

    Page<Study> findByKeyword(String keyword, Pageable pageable);

    List<Study> findByAccount(Set<Tag> tags, Set<Zone> zones);
}
