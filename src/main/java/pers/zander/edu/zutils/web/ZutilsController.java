package pers.zander.edu.zutils.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pers.zander.edu.redis.service.IRedisService;
import pers.zander.edu.utils.HttpClientUtils;
import pers.zander.edu.utils.JSONUtils;
import pers.zander.edu.web.BaseController;

@RestController
@RequestMapping(value = "/zutils")
public class ZutilsController extends BaseController {

	@Autowired
	private IRedisService redis;

	/**
	 * 获取ip
	 * 
	 * @author zhaozhao
	 * @time 2016-6-14 上午10:04:11
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getIp" }, method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> add(HttpServletResponse response, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String url = "https://ipip.yy.com/get_ip_info.php";
		String ipjson = HttpClientUtils.getInstance().getStrResponseBodyAsStream(url, 3000, 3000, "GBK");
		ipjson = ipjson.replaceAll("var returnInfo = ", "");
		ipjson = ipjson.replaceAll(";", "");
		Map<String, String> ipmap = JSONUtils.jsonToMap(ipjson);
		map.put("cip", ipmap.get("cip"));
		map.put("cname", ipmap.get("cname"));
		map.put("country", ipmap.get("country"));
		map.put("province", ipmap.get("province"));
		map.put("city", ipmap.get("city"));
		map.put("isp", ipmap.get("isp"));
		return map;
	}

	/**
	 * 获取redis数据
	 * 
	 * @param response
	 * @param request
	 * @return 2018年4月16日 下午8:20:38
	 */
	@ResponseBody
	@RequestMapping(value = { "/getredis" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String getRedisValue(HttpServletResponse response, HttpServletRequest request) {
		return (String) redis.get("aa");
	}
}
