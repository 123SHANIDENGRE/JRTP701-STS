package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.config.AppConfigProperties;
import com.nt.constants.TravelPlanConstants;
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

	private Map<String, String> messages;


	public TravelPlanMgmtServiceImpl(AppConfigProperties props) {
		messages = props.getMessages();
	
	}

	@Override
	public String registerTravelPlan(TravelPlan plan) {

		TravelPlan saved = travelPlanRepo.save(plan);
		/*
		 * if(saved.getPlanId()!=null) { return
		 * "TravelPlan is saved with the  id value :"+saved.getPlanId(); }else { return
		 * "Problem in saving the TravelPlan"; }
		 */

		return saved.getPlanId() != null ? messages.get(TravelPlanConstants.SAVE_SUCCESS) + saved.getPlanId()
				: messages.get(TravelPlanConstants.SAVE_FAILURE);

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
				.orElseThrow(() -> new IllegalArgumentException(messages.get(TravelPlanConstants.FIND_BY_ID_FAILURE)));

	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		// Update the object
		Optional<TravelPlan> opt = travelPlanRepo.findById(plan.getPlanId());
		if (opt.isPresent()) {
			travelPlanRepo.save(plan);
			return plan.getPlanId() + messages.get(TravelPlanConstants.UPDATE_SUCCESS);

		} else {
			return plan.getPlanId() +messages.get( TravelPlanConstants.UPDATE_FAILURE);
		}
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		// delete the object
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			travelPlanRepo.deleteById(planId);
			return planId +  messages.get(TravelPlanConstants.DELETE_SUCCESS);

		} else {
			return planId + messages.get(TravelPlanConstants.DELETE_FAILURE);
		}
	}

	@Override
	public String changeTravelPlanStatus(Integer planId, String status) {
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			TravelPlan plan = opt.get();
			plan.setActiveSW(status);
			travelPlanRepo.save(plan);
			return   messages.get(TravelPlanConstants.STATUS_CHANGE_SUCCESS);
		} else {
			return planId +  messages.get(TravelPlanConstants.STATUS_CHANGE_FAILURE);
		}
	}

}