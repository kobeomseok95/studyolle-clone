package com.clone.studyolle.account.form;

import lombok.Data;

@Data
public class Notifications {

    private boolean studyCreateByEmail;

    private boolean studyCreatedByWeb;

    private boolean studyEnrollmentResultByEmail;

    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByEmail;

    private boolean studyUpdatedByWeb;
}
