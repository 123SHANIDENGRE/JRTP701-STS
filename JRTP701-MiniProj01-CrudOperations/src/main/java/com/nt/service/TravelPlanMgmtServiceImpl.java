package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.PlanCategory;
import com.nt.entity.TravelPlan;
import com.nt.repository.IPlanCategoryRepository;
import com.nt.repository.ITravelPlanRepository;

@Service
public class TravelPlanMgmtServiceImpl implements ITravelPlanMgmtService {

	@Autowired
	private ITravelPlanRepository travelPlanRepo;

	@Autowired
	private IPlanCategoryRepository planCategoryRepo;

	@Override
	public String registerTravelPlan(TravelPlan plan) {

		TravelPlan saved = travelPlanRepo.save(plan);
		/*
		 * if(saved.getPlanId()!=null) { return
		 * "TravelPlan is saved with the  id value :"+saved.getPlanId(); }else { return
		 * "Problem in saving the TravelPlan"; }
		 */

		return saved.getPlanId() != null ? "Travel plan is saved with id value ::" + saved.getPlanId()
				: "Problem in saving the TourPlan ";

	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {

		// get ALL TravelPlan Categories
		List<PlanCategory> list = planCategoryRepo.findAll();
		Map<Integer, String> categoriesMap = new HashMap<Integer, String>();

		list.forEach(category -> {
			categoriesMap.put(category.getCategoryId(), category.getCategoryName());
		});

		return categoriesMap;
	}

	@Override
	public List<TravelPlan> showAllTravelPlans() {

		return travelPlanRepo.findAll();
	}

	@Override
	public TravelPlan showTravelPlanById(Integer planId) {
		/*
		 * Optional<TravelPlan> opt=travelPlanRepo.findById(planId); if(opt.isPresent())
		 * { return opt.get(); }else { throw new
		 * IllegalArgumentException("plain id not found"); }
		 */

		return travelPlanRepo.findById(planId)
				.orElseThrow(() -> new IllegalArgumentException("Travel Plan is Not Found"));

	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		// Update the object
		Optional<TravelPlan> opt = travelPlanRepo.findById(plan.getPlanId());
		if (opt.isPresent()) {
			travelPlanRepo.save(plan);
			return plan.getPlanId() + "is updated";

		} else {
			return plan.getPlanId() + " Travel Plan is not Found";
		}
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		// delete the object
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			travelPlanRepo.deleteById(planId);
			return planId + "Travel Plan  is  Deleted";

		} else {
			return planId + "  Travel Plan is not Found";
		}
	}

	@Override
	public String changeTravelPlanStatus(Integer planId, String status) {
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			TravelPlan plan = opt.get();
			plan.setActiveSW(status);
			travelPlanRepo.save(plan);
			return "Travel Plan  Status is Changed";
		} else {
			return planId + " Travel Plan is not found  updation";
		}
	}

}
