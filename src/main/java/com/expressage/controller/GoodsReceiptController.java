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

import com.expressage.pojo.GoodsReceipt;
import com.expressage.service.GoodsReceiptService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
@Controller
public class GoodsReceiptController {
	@Resource
	GoodsReceiptService service;
	
	/**
	 * 查询
	 * @param model
	 * @return
	 */
	@RequestMapping("selReceipt")
	public String findReceipt(Model model) {
		List<GoodsReceipt> list = service.findReceipt();
		model.addAttribute("listl", list);
		return "report/report_list3";
	}
	
	@RequestMapping("deleReceipt")
	public String deleReceipt(int cid,HttpServletResponse response) throws IOException {
        service.deleReceipt(cid);
		return "redirect:selReceipt";
		}
	/**
	 * 回执单导出
	 * 
	 * @return
	 */
	@RequestMapping("jxlWriteMenu3")
	public String jxlWriteMenu() {
		List<GoodsReceipt> list = service.findReceipt();
		// 准备设置excel工作表的标题
		String[] title = { "货运回执单编号", "订单编号", "差错类别", "件数", "规格","货物价值" };
		try {
			// 获得开始时间
			// long start = System.currentTimeMillis();
			// 输出的excel的路径
			String filePath = "D:\\DAORU\\运货差错信息.et";
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
			for (GoodsReceipt goods : list) {
				int i = 0;
				label = new Label(i, j, goods.getCid().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getReceiptcode());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getOrderno());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getError());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getPieceamount().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getSpecifications());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getGoodsvalue().toString());
				i++;
				j++;
			}
			// 写入数据
			wwb.write();
			// 关闭文件
			wwb.close();
		} catch (Exception e) {
			System.out.println("---出现异常---");
			e.printStackTrace();
		}
		return "redirect:selReceipt";
	}
}
