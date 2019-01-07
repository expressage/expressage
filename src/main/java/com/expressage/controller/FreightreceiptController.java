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

import com.expressage.pojo.Freightreceipt;
import com.expressage.service.FreightreceiptService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
@Controller
public class FreightreceiptController {
	@Resource
	FreightreceiptService service;
	
	@RequestMapping("selFreig")
	public String find(Model model) {
		List<Freightreceipt> list = service.findFreig();
		model.addAttribute("list", list);
		return "report/report_list";
	}
	
	@RequestMapping("delFreig")
	 public String deleFreig(@RequestParam("hid")int hid,HttpServletResponse response) throws IOException {
		int num = service.delFreig(hid);
		if(num>0){
			response.getWriter().print("<script>alert('删除成功!');location='selFreig'</script>");
		}else{
			response.getWriter().print("<script>alert('删除失败!');location='selFreig'</script>");
		}
		return "report/report_list";
	 }
	/**
	 * 回执单导出
	 * 
	 * @return
	 */
	@RequestMapping("jxlWriteMenus")
	public String jxlWriteMenu() {
		List<Freightreceipt> list = service.findFreig();
		// 准备设置excel工作表的标题
		String[] title = {"货运回执单编号","装货地点","起运时间","交货地点","到达时间","司机ID","回执单状态"};
		try {
			// 获得开始时间
			// long start = System.currentTimeMillis();
			// 输出的excel的路径
			String filePath = "D:\\DAORU\\货运回执单.et";
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
			for (Freightreceipt freightreceipt : list) {
				int i = 0;
				label = new Label(i, j, freightreceipt.getHid().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getReceiptcode());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getLoadstation());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getStartcarrydate().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getDealstation());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getArrivedate().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getDid().toString());
				i++;
				sheet.addCell(label);
				label = new Label(i, j, freightreceipt.getBillstate());
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
		return "report/report_list";
	}
}
