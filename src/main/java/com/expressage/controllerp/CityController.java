package com.expressage.controllerp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expressage.pojo.City;
import com.expressage.service.CityService;

@Controller
@RequestMapping("/City")
public class CityController {
	@Autowired
	private CityService cityService;

	@RequestMapping("/zmGetCity")
	@ResponseBody
	public String zm_getCity() {
		String city = "[";
		List<City> listC1 = cityService.zm_findCityByFid(0);
		for (int i = 0; i < listC1.size(); i++) {
			City city1 = listC1.get(i);
			city += "{\"n\":\"" + city1.getCityname() + "\",\"c\":[";
			List<City> listC2 = cityService.zm_findCityByFid(city1.getCid());
			for (int j = 0; j < listC2.size(); j++) {
				City city2 = listC2.get(j);
				city += "{\"n\":\"" + city2.getCityname() + "\",\"a\":[";
				List<City> listC3 = cityService.zm_findCityByFid(city2.getCid());
				for (int k = 0; k < listC3.size(); k++) {
					City city3 = listC3.get(k);
					city += "\"" + city3.getCityname() + "\"";
					if (k != listC3.size() - 1) {
						city += ",";
					}
				}
				city += "]}";
				if (j != listC2.size() - 1) {
					city += ",";
				}
			}
			city += "]}";
			if (i != listC1.size() - 1) {
				city += ",";
			}
		}
		city += "]";
		return city;
	}
}
