package com.clone.studyolle.study.event;

import com.clone.studyolle.study.Study;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudyCreatedEvent {

    private final Study study;
}
