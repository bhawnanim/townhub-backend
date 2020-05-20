package com.townhublisting.plans.repository;

import com.townhublisting.plans.model.Plans;

import java.util.List;

public interface PlansRepo {
    List<Plans> listAllPlans() throws Exception;

    Integer savePlan(Plans plans) throws Exception;

    Integer updatePlan(int planId, Plans plans) throws Exception;

    Integer changePlanStatus(int planId, boolean planStatus) throws Exception;
}
