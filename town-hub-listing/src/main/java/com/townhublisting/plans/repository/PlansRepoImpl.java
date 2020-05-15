package com.townhublisting.plans.repository;

import com.townhublisting.plans.model.Plans;
import com.townhublisting.plans.model.mapper.PlansRowMapper;
import com.townhublisting.plans.service.PlansProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "PlansRepo")
public class PlansRepoImpl implements PlansRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PlansProperties serviceProperties;

    @Override
    public List<Plans> listAllPlans() throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetPlans(), new PlansRowMapper());
    }

    @Override
    public Integer savePlan(Plans plans) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getInsertPlan(), plans.getPlanName(), plans.getPlanAmount(), plans.getPlanDescription(), plans.isPlanActive(), plans.getPlanListingLimit(), plans.getPlanDuration(), plans.isReviewShow(), plans.isContactShow());
    }

    @Override
    public Integer updatePlan(int planId, Plans plans) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdatePlan(), plans.getPlanName(), plans.getPlanAmount(), plans.getPlanDescription(), plans.isPlanActive(), plans.getPlanListingLimit(), plans.getPlanDuration(), plans.isReviewShow(), plans.isContactShow(), planId);
    }

    @Override
    public Integer changePlanStatus(int planId, boolean planStatus) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getChangePlanStatus(), planStatus, planId);
    }
}
