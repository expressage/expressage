package com.expressage.controllerp;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.expressage.pojo.Address;
import com.expressage.pojo.Order;
import com.expressage.pojo.Tracking;
import com.expressage.pojo.User;
import com.expressage.service.AddressService;
import com.expressage.service.OrderService;
import com.expressage.service.TrackingService;
import com.expressage.service.UserService;
import com.expressage.util.HttpClientUtil;

@Controller
@RequestMapping("/Personal")
public class PersonalController {
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private TrackingService trackingService;

	// 登录
	@RequestMapping("/zmLogin")
	public String zmLogin(@RequestParam("tel") String tel, HttpSession session) {
		User user = userService.zm_login(tel);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:zmPersonal";
		} else {
			return "proscenium/login";
		}
	}

	// 查询电话是否存在
	@RequestMapping("/zmCheckTel")
	@ResponseBody
	public int zmCheckTel(@RequestParam("tel") String tel) {
		return userService.zm_selUserByTel(tel);
	}

	// 注册
	@RequestMapping("/zmRegister")
	public String zmRegister(@RequestParam("tel") String tel, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		user.setTel(tel);
		user.setUname(tel.replaceAll("(\\d{3})\\d{6}(\\d{2})", "$1****$2"));
		userService.zm_addUser(user);
		return "proscenium/index";
	}

	// 获取短信验证
	@RequestMapping("/zmGetCode")
	@ResponseBody
	public String zmGetCode(@RequestParam("tel") String tel) {
		Random random = new Random();
		String code = random.nextInt(10000) + "";
		if (code.length() < 4) {
			for (int i = 1; i <= 4 - code.length(); i++)
				code = "0" + code;
		}
		// 用户名
		String uid = "dxjk";
		// key
		String key = "d41d8cd98f00b204e980";
		// 手机号码
		String smsMob = tel;
		// 短信内容
		String smsText = "亲爱的客户：您好！你的手机验证码为" + code + "。请勿将验证码告知他人并确定该申请为您本人操作。";

		HttpClientUtil client = HttpClientUtil.getInstance();
		// 调用方法
		/*int num = client.sendMsgUtf8(uid, key, smsText, smsMob);
		System.out.println(num);*/
		System.out.println(code);
		return code;
	}

	// 跳转个人中心
	@RequestMapping("/zmPersonal")
	public String zmPersonal(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		} else {
			List<Address> listA = addressService.zm_selAddress(user.getUid());
			model.addAttribute("user", user);
			model.addAttribute("address", listA.get(0));
			return "proscenium/personal";
		}
	}

	// 查询登录人的地址
	@RequestMapping("/zmSelAddress")
	public String zm_selAddress(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		List<Address> listA = addressService.zm_selAddress(user.getUid());
		model.addAttribute("listA", listA);
		return "proscenium/address";
	}

	// 查询地址详情
	@RequestMapping("/zmSelAddressByAid")
	public String zm_selAddressByAid(Integer aid, Model model) {
		Address address = addressService.zm_selAddressByAid(aid);
		model.addAttribute("address", address);
		return "proscenium/updAddress";
	}

	// 添加地址信息
	@RequestMapping("/zmAddaddress")
	public String zm_addAddress(Address address, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		address.setUid(user.getUid());
		addressService.zm_addAddress(address);
		if (address.getIsdefault() == 0) {
			addressService.zm_updIsdefault1(address.getAid(), 1);
		}
		return "redirect:zmSelAddress";
	}

	// 修改地址信息
	@RequestMapping("/zmUpdAddress")
	public String zm_updAddress(Address address, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		addressService.zm_updAddress(address);
		if (address.getIsdefault() == 0) {
			addressService.zm_updIsdefault1(address.getAid(), user.getUid());
		}
		return "redirect:zmSelAddress";
	}

	// 修改为默认地址
	@RequestMapping("/zmUpdIsdefault")
	public String zm_updIsdefault(Address address, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		addressService.zm_updIsdefault1(address.getAid(), user.getUid());
		addressService.zm_updIsdefault0(address.getAid());
		return "redirect:zmSelAddress";
	}

	// 删除地址信息
	@RequestMapping("/zmDelAddress")
	public String zm_delAddress(@RequestParam("aid") Integer aid) {
		addressService.zm_delAddress(aid);
		return "redirect:zmSelAddress";
	}

	// 获取登录人的地址总数
	@RequestMapping("/zmGetCount")
	@ResponseBody
	public int zm_getCount(HttpSession session) {
		User user = (User) session.getAttribute("user");
		int count = addressService.zm_getCount(user.getUid());
		return count;
	}

	// 查询我的快递
	@RequestMapping("/zmGetOrder")
	public String zm_getOrder(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		List<Order> listO = orderService.zm_selOrder(user.getUid());
		model.addAttribute("listO", listO);
		return "proscenium/order";
	}

	// 查看物流详情(物流跟踪)
	@RequestMapping("/zmSelTrackByOid")
	public String zm_selTrackByOid(@RequestParam("oid") Integer oid, Model model) {
		Order order = orderService.zm_getOrderByOid(oid);
		List<Tracking> listT = trackingService.zm_selTrackByOid(oid);
		model.addAttribute("listT", listT);
		model.addAttribute("order", order);
		return "proscenium/track";
	}

	// 到订单里修改或添加备注
	@RequestMapping("/zmOrderRemarks")
	@ResponseBody
	public int zm_orderRemarks(@RequestParam("oid") Integer oid, @RequestParam("remarks") String remarks) {
		return orderService.zm_orderRemarks(oid, remarks);
	}

	// 查询快递详情
	@RequestMapping("/zmGetOrderByOid")
	public String zm_getOrderByOid(@RequestParam("oid") Integer oid, Model model) {
		Order order = orderService.zm_getOrderByOid(1);
		model.addAttribute("order", order);
		return "proscenium/orderDetails";
	}

	// 删除我的订单
	@RequestMapping("/zmDelOrder")
	public String zm_delOrder(Integer oid) {
		orderService.zm_delOrder(oid);
		return "redirect:zmGetOrder";
	}

	// 根据用户id查询用户个人信息
	@RequestMapping("/zmSelUserByUid")
	public String zm_selUserByUid(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		user = userService.zm_selUserByUid(user.getUid());
		model.addAttribute("user", user);
		return "/proscenium/userInfo";
	}

	// 修改用户个人信息
	@RequestMapping("/zmUpdUser")
	public String zm_updUser(@RequestParam(value = "img") MultipartFile img, String uname, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/Jump/zmJumpLogin";
		}
		user.setUname(uname);
		String path;
		String oldImg = img.getOriginalFilename();
		user.setImg(oldImg);
		try {
			path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/proscenium/images";
			File file = new File(path, oldImg);
			if (!file.getParentFile().exists()) {
				file.mkdir();
			}
			img.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.zm_updUser(user);
		return "redirect:zmSelUserByUid";
	}
}
