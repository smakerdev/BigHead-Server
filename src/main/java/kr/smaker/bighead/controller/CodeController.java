package kr.smaker.bighead.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
		String imei = request.getParameter("imei");
		JSONObject obj = new JSONObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		obj.put("success", false);
		map.put("code", code);
		map.put("imei", imei);
		if (code != null && imei != null) {
			
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
		
		if (imeidb != null && imeidb == imei) {
			obj.put("success", true);
			return new UTF8Response(obj.toJSONString(), "json").entity;
		}
		return new UTF8Response(obj.toJSONString(), "json").entity;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<String> listCode() {
		return null;
	}
}
