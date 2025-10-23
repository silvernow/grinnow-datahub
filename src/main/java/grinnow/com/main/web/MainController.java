package grinnow.com.main.web;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.property.EgovPropertyService;
import grinnow.com.cmm.service.GnnMessageSource;
import grinnow.com.common.amazon.MongoDbClient;
import grinnow.com.common.service.CommonService;
import grinnow.com.main.service.MainService;

@Controller
@RequestMapping(value="/main")
public class MainController {
	
	@Resource(name = "gnnMessageSource")
	private GnnMessageSource gnnMessageSource;
	
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "commonService")
	private CommonService commonService;
    
    @Resource(name = "mongoDbClient")
    private MongoDbClient mongoDbClient;
    
    @Resource(name = "mainService")
	private MainService mainService;
	
    /**
     * 엑셀 업로드 처리
     */
    @ResponseBody
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public Map<String, Object> uploadExcel(@RequestParam("excelFile") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        int insertCount = 0;

        try {
            if (file.isEmpty()) {
                result.put("result", "fail");
                result.put("message", "파일이 비어 있습니다.");
                return result;
            }

            InputStream is = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            List<Map<String, Object>> dataList = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, Object> map = new HashMap<>();
                map.put("treeCd", safeString(getCellValue(row.getCell(0))));
                map.put("treeUnit", safeString(getCellValue(row.getCell(1))));
                map.put("treeQty", safeInteger(getCellValue(row.getCell(2))));
                map.put("treeUnitPrice", safeBigDecimal(getCellValue(row.getCell(3))));
                map.put("treePrice", safeBigDecimal(getCellValue(row.getCell(4))));
                map.put("treeAddrCd", safeInteger(getCellValue(row.getCell(5))));
                map.put("basisDt", isNull(getCellValue(row.getCell(6))));
                map.put("unitStdCd", safeString(getCellValue(row.getCell(7))));
                map.put("startAreaId", safeInteger(getCellValue(row.getCell(8))));
                map.put("endAreaId", safeInteger(getCellValue(row.getCell(9))));
                map.put("arborRootCm", safeBigDecimal(getCellValue(row.getCell(10))));
                map.put("arborBstCm", safeBigDecimal(getCellValue(row.getCell(11))));
                map.put("arborHeiMm", safeBigDecimal(getCellValue(row.getCell(12))));
                map.put("shrubWidMm", safeBigDecimal(getCellValue(row.getCell(13))));
                map.put("shrubHeiMm", safeBigDecimal(getCellValue(row.getCell(14))));
                map.put("sapRootCm", safeBigDecimal(getCellValue(row.getCell(15))));
                map.put("creId", "excel");
                dataList.add(map);
            }

            workbook.close();
            insertCount = mainService.insertTreePriceList(dataList);

            result.put("result", "success");
            result.put("pgCount", insertCount);
            result.put("mongoCount", insertCount);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "fail");
            result.put("message", e.getMessage());
        }

        return result;
    }


    @ResponseBody
    @GetMapping("/checkMongo")
    public Map<String, Object> checkMongo() {
        Map<String, Object> res = new HashMap<>();
        try {
            mongoDbClient.ping();
            res.put("status", "online");
        } catch (Exception e) {
            res.put("status", "offline");
            res.put("error", e.getMessage());
        }
        return res;
    }
    
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }
	
    private String safeString(String val) {
        return (val == null || val.trim().isEmpty()) ? null : val.trim();
    }

    private Integer safeInteger(String val) {
        try {
            if (val == null || val.trim().isEmpty()) return null;
            return Integer.valueOf(val.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private BigDecimal safeBigDecimal(String val) {
        try {
            if (val == null || val.trim().isEmpty()) return null;
            return new BigDecimal(val.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static String isNull(Object param) {
		String str = "";
		
		if (param == null) {
			return "";
		}
		
		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date)param).toString();
		} else {
			str = String.valueOf(param);
		}
		
		if (str.equals("")) {
			return "";
		} else {
			return str.trim();
		}
	}
}