package com.haw.mvsspring.controller;

import com.haw.mvsspring.model.Adder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	@PostMapping("/subtraction")
	public int getDifference(@RequestBody HashMap<String, Integer> myMap)
	{
		final int a = myMap.get("firstInt");
		final int b = myMap.get("secondInt");

		return a - b;
	}


	@GetMapping("/api/v1/songs")
	public ArrayList<String> getSongList()
	{
		ArrayList<String> songList = new ArrayList<>();
		File folder = new File("insert folder path" );	//what folder should we use? ..../src/resources/songFolder?
		FileFilter fileFilter = new WildcardFileFilter("*.mp3");
		File[] files = folder.listFiles(fileFilter);

		for(int i = 0; i < files.length; i++)
		{
			songList.add(files[i].getName());
		}
		return songList;
	}
}
