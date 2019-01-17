package com.expressage.controllerp;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expressage.pojo.Address;
import com.expressage.pojo.City;
import com.expressage.pojo.Order;
import com.expressage.pojo.Price;
import com.expressage.pojo.ProductType;
import com.expressage.pojo.Tracking;
import com.expressage.pojo.User;
import com.expressage.service.AddressService;
import com.expressage.service.CityService;
import com.expressage.service.OrderService;
import com.expressage.service.PriceService;
import com.expressage.service.ProductTypeService;
import com.expressage.service.TrackingService;

@Controller
@RequestMapping("/Item")
public class ItemController {
	@Autowired
	private TrackingService trackingService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CityService cityService;
	@Autowired
	private PriceService priceService;

	private Address address1;

	private Address address2;

	private List<ProductType> listP;

	private Integer address = 0;

	// 根据订单编号查询快递跟踪信息
	@RequestMapping("/zmSelTrack")
	public String zm_selTrackByOrderNo(@RequestParam("orderno") String orderno, Model model) {
		List<Tracking> listT = trackingService.zm_selTrackByOrderno(orderno);
		model.addAttribute("listT", listT);
		return "/proscenium/track2";
	}

	// 查询商品类型
	@RequestMapping("/zmGetProductType")
	private String zm_getProductType(Model model) {
		listP = productTypeService.zm_getProductType();
		model.addAttribute("listP", listP);
		return "/proscenium/mailing";
	}

	// 获取地址
	@RequestMapping("/zmGetAddress")
	private String zm_getAddress(Integer address, HttpSession session, Model model) {
		this.address = address;
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		List<Address> listA = addressService.zm_selAddress(user.getUid());
		model.addAttribute("listA", listA);
		return "/proscenium/choiceAddress";
	}

	// 通过aid获取地址
	@RequestMapping("/zmGetAddressByAid")
	private String zm_getAddressByAid(Integer aid, Model model) {
		if (address == 1) {
			address1 = addressService.zm_selAddressByAid(aid);
		} else if (address == 2) {
			address2 = addressService.zm_selAddressByAid(aid);
		} else {
			return "redirect:/Jump/zmJumpLogin";
		}
		model.addAttribute("address1", address1);
		model.addAttribute("address2", address2);
		model.addAttribute("listP", listP);
		return "/proscenium/mailing";
	}

	// 添加订单到order
	@RequestMapping("/zmAddOrder")
	private String zm_addOrder(Order order, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		Random random = new Random();
		String orderno = random.nextInt(10000) + "";
		if (orderno.length() < 8) {
			for (int i = 1; i <= 8 - orderno.length(); i++)
				orderno = "0" + orderno;
		}
		order.setEprice(order.getWeight() * 12);
		order.setStatus("已预约");
		order.setOrderno("ex" + orderno);
		order.setUid(user.getUid());
		orderService.zm_addOrder(order);
		Tracking tracking = new Tracking();
		tracking.setInfo("下单成功，正在为您分配快递员");
		tracking.setOrderno(order.getOid() + "");
		trackingService.zm_addTrack(tracking);
		return "redirect:/Jump/zmJumpIndex";
	}

	@RequestMapping("/zmSelCity")
	public String zm_selCity(Model model) {
		List<City> listC = cityService.zm_findCityByFid(0);
		model.addAttribute("listC", listC);
		return "/proscenium/selPrice";
	}

	@RequestMapping("/zmSelPrice")
	@ResponseBody
	public Price zm_selPrice(@RequestParam("address1") String address1, @RequestParam("address2") String address2) {
		return priceService.zm_selPriceByAddress(address1, address2);
	}

}
