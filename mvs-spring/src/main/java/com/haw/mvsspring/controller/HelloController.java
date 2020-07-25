package com.haw.mvsspring.controller;

import com.haw.mvsspring.model.Adder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping
@RestController
public class HelloController {

	@PostMapping("/sum")
	public int getSum(@RequestBody Adder myAdder)
	{
		final int a = myAdder.getFirstInt();
		final int b = myAdder.getSecondInt();

		return a + b;
	}

	@PostMapping("/subtraction")
	public int getDifference(@RequestBody HashMap<String, Integer> myMap)
	{
		final int a = myMap.get("firstInt");
		final int b = myMap.get("secondInt");

		return a - b;
	}
}
