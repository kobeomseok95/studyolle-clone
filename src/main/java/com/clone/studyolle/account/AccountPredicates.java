package com.clone.studyolle.account;

import com.clone.studyolle.tag.Tag;
import com.clone.studyolle.zone.Zone;

import java.util.Set;
import com.querydsl.core.types.Predicate;

public class AccountPredicates {

    public static Predicate findByTagsAndZones(Set<Tag> tags, Set<Zone> zones) {
        QAccount account = QAccount.account;
        return account.zones.any().in(zones)
                .and(account.tags.any().in(tags));
    }
}
