package com.townhublisting.plans.model.mapper;

import com.townhublisting.plans.model.Plans;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlansRowMapper implements RowMapper<Plans> {
    @Override
    public Plans mapRow(ResultSet rs, int i) throws SQLException {
        Plans plans = new Plans();
        plans.setPlanId(rs.getInt("planId"));
        plans.setPlanName(rs.getString("planName"));
        plans.setPlanAmount(rs.getString("planAmount"));
        plans.setPlanDescription(rs.getString("planDescription"));
        plans.setPlanActive(rs.getBoolean("planActive"));
        plans.setPlanListingLimit(rs.getInt("planListingLimit"));
        plans.setPlanDuration(rs.getString("planDuration"));
        plans.setReviewShow(rs.getBoolean("reviewShow"));
        plans.setContactShow(rs.getBoolean("contactShow"));
        return plans;
    }
}
