package com.haw.mvsspring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.haw.mvsspring.model.Song;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvsSpringApplicationTests {
	static File directory, song1, song2, song3;

	@BeforeAll
	public static void init() throws IOException {
		directory = new File("../MVS-WebApp/songs/test/");
		directory.mkdir();
		song1 = new File("../MVS-WebApp/songs/test/song1.mp3");
		song1.createNewFile();
		song2 = new File("../MVS-WebApp/songs/test/song2.mp3");
		song2.createNewFile();
		song3 = new File("../MVS-WebApp/songs/test/song3.mp3");
		song3.createNewFile();

	}

	/*@Test
	void readListOfSongs() throws IOException {

		final ArrayList<Song> songsList = SongList.init("../MVS-WebApp/songs/test/");
		assertEquals(songsList.get(0).getRawName(), "song1.mp3");
		assertEquals(songsList.get(1).getRawName(), "song2.mp3");
		assertEquals(songsList.get(2).getRawName(), "song3.mp3");
	}

	@Test
	void readListOfSongsWithNonMp3Files() throws IOException {
		final File file = new File("../MVS-WebApp/songs/test/text.txt");
		file.createNewFile();
		final ArrayList<Song> songsList = SongList.init("../MVS-WebApp/songs/test/");
		final Song s = songsList.stream().filter(song -> "text.txt".equals(song.getRawName()) ).findAny().orElse(null);
		file.delete();
		assertNull(s);
	}*/

	@AfterAll
	public static void done() {
		song1.delete();
		song2.delete();
		song3.delete();
		directory.delete();
	}

}
