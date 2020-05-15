package com.townhublisting.plans.service;

import com.townhublisting.plans.model.Plans;
import com.townhublisting.plans.repository.PlansRepo;
import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlansService {

    @Autowired
    PlansRepo plansRepo;

    public Result<List<Plans>> listAllPlans() throws Exception {
        List<Plans> list = plansRepo.listAllPlans();
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Plans are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Plans not found")))));
        }
        return result;
    }

    public Result<Integer> savePlan(Plans plans) throws Exception {
        int result = plansRepo.savePlan(plans);
        if (result != 0) {
            return new Result<>(201, "Record successfully saved.");
        } else {
            throw new ResultException(new Result<>(400, "Please record plan information again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record plan information again!!!")))));
        }
    }

    public Result<Integer> updatePlan(int id, Plans plans) throws Exception {
        int result = plansRepo.updatePlan(id, plans);
        if (result != 0) {
            return new Result<>(201, "Record successfully edited.");
        } else {
            throw new ResultException(new Result<>(400, "Please record plan information again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record plan information again!!!")))));
        }
    }

    public Result<Integer> changePlanStatus(int id, boolean planStatus) throws Exception {
        int result = plansRepo.changePlanStatus(id, planStatus);
        if (result != 0) {
            return new Result<>(201, "Plan Status updated.");
        } else {
            throw new ResultException(new Result<>(400, "Please record plan status again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record plan status again!!!")))));
        }
    }
}
