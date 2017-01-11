package kr.smaker.bighead.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.smaker.bighead.manager.DBService;
import kr.smaker.bighead.manager.UTF8Response;

@Controller
@RequestMapping(value = "/API/Code")
public class CodeController {

	@Autowired
	private DBService db;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<String> createCode(HttpServletRequest request) throws DuplicateKeyException {
		String code = request.getParameter("code");
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		obj.put("success", false);
		map.put("code", code);
		map.put("imei", null);
		if (code != null) {

			try {
				db.insertCode(map);
				obj.put("success", true);
				return new UTF8Response(obj.toJSONString(), "json").entity;
			} catch (Exception e) {
				return new UTF8Response(obj.toJSONString(), "json").entity;
			}
		}
		return new UTF8Response(obj.toJSONString(), "json").entity;
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ResponseEntity<String> checkCode(HttpServletRequest request) throws DuplicateKeyException {
		String code = request.getParameter("code");
		String imei = request.getParameter("imei");
		String imeidb = null;
		JSONObject obj = new JSONObject();
		obj.put("success", false);

		try {
			imeidb = db.selectImei(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (imeidb != null && imeidb.equals(imei)) {
			obj.put("success", true);
			return new UTF8Response(obj.toJSONString(), "json").entity;
		}
		return new UTF8Response(obj.toJSONString(), "json").entity;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ResponseEntity<String> registerCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		String imei = request.getParameter("imei");
		String imeidb = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = new JSONObject();
		obj.put("success", false);

		try {
			imeidb = db.selectImei(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (imeidb == null) {
			try {
				map.put("code", code);
				map.put("imei", imei);
				db.updateImei(map);
				
				obj.put("success", true);
				return new UTF8Response(obj.toJSONString(), "json").entity;
			} catch (Exception e) {
				return new UTF8Response(obj.toJSONString(), "json").entity;
			}
		}
		return new UTF8Response(obj.toJSONString(), "json").entity;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<String> listCode(ModelMap modelMap, Map<String, Object> commandMap,
			HttpServletRequest request) throws Exception {
		List<Map<String, Object>> list = db.selectList(commandMap);
		JSONObject obj = new JSONObject();
		obj.put("data", list.toString());
		return new UTF8Response(obj.toJSONString(), "json").entity;
	}
}
