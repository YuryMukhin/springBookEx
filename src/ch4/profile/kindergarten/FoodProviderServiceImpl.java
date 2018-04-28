package ch4.profile.kindergarten;

import ch4.profile.Food;
import ch4.profile.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<Food>();
        lunchSet.add(new Food("milk"));
        lunchSet.add(new Food("Biscuits"));
        return lunchSet;
    }
}
