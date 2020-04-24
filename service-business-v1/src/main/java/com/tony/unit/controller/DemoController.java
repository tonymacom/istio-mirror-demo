package com.tony.unit.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping
@Slf4j
public class DemoController {

	@Value("${spring.application.name}")
	private String application;

	private AtomicInteger count = new AtomicInteger(0);

	@GetMapping("/request/info")
	public ResponseEntity<String> info(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			log.info("{} = {}",name,request.getHeader(name));
			result.put(name, request.getHeader(name));
		}

		return ResponseEntity.ok(JSON.toJSONString(result));
	}

	@GetMapping("/request/times")
	public ResponseEntity<String> times() {
		int i = count.incrementAndGet();
		String info = String.format("receive %s times request", i);
		log.info(info);
		return ResponseEntity.ok(info);
	}

	@GetMapping("/request/times/reset")
	public ResponseEntity<String> reset() {
		count.set(0);
		log.info("reset 0");
		return ResponseEntity.ok("reset 0");
	}
}
