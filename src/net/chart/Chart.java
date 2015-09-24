package net.chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

public class Chart {
	private String content;
	private String template;
	private String identifier;
	private String type;
	private Map<String, String> options;
	private List<List<Object>> datas;
	private String strOptions = "title:'sans titre'";

	public Chart(String identifier) {
		this.identifier = identifier;
		options = new HashMap<>();
		type = "PieChart";
		datas = new ArrayList<List<Object>>();
		content = "";
		template = ""
				+ "drawChart=function() {"
				+ "var data = google.visualization.arrayToDataTable({data});"
				+ "var options = {options};"
				+ "var chart = new google.visualization.{type}($('{identifier}'));"
				+ "chart.draw(data, options);"
				+ "}";
	}

	public void generate() {
		if ("".equals(strOptions)) {
			JSONArray jsOptions = new JSONArray(Arrays.asList(options));
			strOptions = jsOptions.toString();
			strOptions = strOptions.substring(1, strOptions.length() - 1);
		}
		JSONArray jsDatas = new JSONArray(Arrays.asList(datas));
		String strDatas = jsDatas.toString();
		strDatas = strDatas.substring(1, strDatas.length() - 1);
		content = template.replace("{options}", strOptions);
		content = content.replace("{data}", strDatas);
		content = content.replace("{identifier}", identifier);
		content = content.replace("{type}", type);
	}

	public void addOption(String key, String value) {
		options.put(key, value);
	}

	public void addData(Object... values) {
		List<Object> tmpData = Arrays.asList(values);
		datas.add(tmpData);
	}

	public String getContent() {
		return content;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		generate();
		return content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static boolean isNumeric(String str) {
		try
		{
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}

	public String getStrOptions() {
		return strOptions;
	}

	public void setStrOptions(String strOptions) {
		this.strOptions = strOptions;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
