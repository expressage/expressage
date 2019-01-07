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
import org.springframework.web.bind.annotation.RequestParam;

import com.expressage.pojo.GoodsInformation;
import com.expressage.service.GoodsInformationService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
@Controller
public class GoodsInformationController {
	@Resource
	GoodsInformationService service;

	@RequestMapping("selGoods")
	public String findGoods(Model model) {
		List<GoodsInformation> list = service.findGoods();
		model.addAttribute("lists", list);
		return "report/report_list1";
	}

	@RequestMapping("delGoods")
	public String deleGoods(@RequestParam("xid") int xid, HttpServletResponse response) throws IOException {
		int num = service.deleGoods(xid);
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
	@RequestMapping("jxlWriteMenu2")
	public String jxlWriteMenu() {
		List<GoodsInformation> list = service.findGoods();
		// 准备设置excel工作表的标题
		String[] title = { "货运回执单编号", "快递员名称", "验收货物记录", "收货人", "收货日期" };
		try {
			// 获得开始时间
			// long start = System.currentTimeMillis();
			// 输出的excel的路径
			String filePath = "D:\\DAORU\\货物回执单信息.et";
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
			for (GoodsInformation goods : list) {
				int i = 0;
				label = new Label(i, j, goods.getXid().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getRevertcode());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getEid().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getCheckgoodsrecord());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getConsignee());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, goods.getConsigneedate().toString());
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
