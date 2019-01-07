package com.expressage.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expressage.pojo.Customerreceipt;
import com.expressage.service.CustomerreceiptService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
@Controller
public class CustomerreceiptController {
	@Resource
	CustomerreceiptService service;
	/**
	 * 查询
	 * @param model
	 * @return
	 */
	@RequestMapping("selCustomers")
	public String findCustomer(Model model) {
		List<Customerreceipt> list = service.SelCustomer();
		model.addAttribute("list", list);
		return "report/report_list2";
	}
	
	@RequestMapping("del")
	public String deleCustomer(int kid,HttpServletResponse response) throws IOException {
		int num = service.delCustomer(kid);
		if (num > 0) {
			response.getWriter().print("<script>alert('删除成功!');location='selGoods'</script>");
		} else {
			response.getWriter().print("<script>alert('删除失败!');location='selGoods'</script>");
		}
		return "report/report_list";
		}
	
	/**
	 * 回执单导出
	 * 
	 * @return
	 */
	@RequestMapping("jxlWriteMenu1")
	public String jxlWriteMenu() {
		List<Customerreceipt> list = service.SelCustomer();
		// 准备设置excel工作表的标题
		String[] title = { "货运回执单编号", "寄货地址", "收货地址", "验收货物记录", "收货人","收货日期" };
		try {
			// 获得开始时间
			// long start = System.currentTimeMillis();
			// 输出的excel的路径
			String filePath = "D:\\DAORU\\客户回执单.et";
			// 创建Excel工作薄
			WritableWorkbook wwb;
			// 新建立一个jxl文件,即在E盘下生成test.xls
			OutputStream os = new FileOutputStream(filePath);
			wwb = Workbook.createWorkbook(os);
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet("ID", 0);
			Label label;
			for (int i = 0; i < title.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(i, 0, title[i]);
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
			}
			// 填充数据
			/**
			 * 保存数字到单元格，需要使用jxl.write.Number 必须使用其完整路径，否则会出现错误
			 */
			int j = 1;
			for (Customerreceipt cust : list) {
				int i = 0;
				label = new Label(i, j, cust.getKid().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, cust.getOrderno());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, cust.getMaddress().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, cust.getRaddress().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, cust.getCheckgoodsrecord());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, cust.getConsignee());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, cust.getConsigneedate().toString());
				i++;
			}
			// 写入数据
			wwb.write();
			// 关闭文件
			wwb.close();
		} catch (Exception e) {
			System.out.println("---出现异常---");
			e.printStackTrace();
		}
		return "report/report_list";
	}
}
