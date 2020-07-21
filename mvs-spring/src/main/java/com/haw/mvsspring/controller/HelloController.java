package com.haw.mvsspring.controller;

import com.haw.mvsspring.model.Adder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RequestMapping
@RestController
public class HelloController {

	@GetMapping("/")
	public String index()
	{
		return "WebApp says hello!";
	}

	@PostMapping("/sum")
	public int getSum(@RequestBody Adder myAdder)
	{
		final int a = myAdder.getFirstInt();
		final int b = myAdder.getSecondInt();

		return a + b;
	}

	@GetMapping("/subtraction")
	public int getDifference(@RequestBody HashMap<String, Integer> myMap)
	{
		int a = myMap.get("firstInt");
		int b = myMap.get("secondInt");

		return a - b;
	}
}
