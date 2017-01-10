package kr.smaker.bighead.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.smaker.bighead.manager.DBService;

@Controller
@RequestMapping(value = "/API/Code")
public class CodeController {
	
	@Autowired
	private DBService db;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<String> createCode(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		String imei = request.getParameter("imei");
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		map.put("code", code);
		map.put("imei", imei);
		if (code != null && imei != null) {
			db.insertCode(map);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ResponseEntity<String> checkCode() {
		return null;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<String> listCode() {
		return null;
	}
}
