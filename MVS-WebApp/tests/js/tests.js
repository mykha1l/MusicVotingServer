describe("create songs list", function() {

    it("find songListContainer", function() {
      const songListContainer = document.createElement("div");
      songListContainer.className = "songlist";
      songListContainer.hidden = true;
      document.body.appendChild(songListContainer);
      assert.notEqual(findSongListContainer(), undefined);
    });

    it("fill songListContainer", function() {
        const songListContainer = findSongListContainer();
        const songs = [{ rawName: "song1" }, { rawName: "song2" }, { rawName: "song3" }];
        createSongList(songs, songListContainer);
        assert.equal(document.getElementsByClassName("song")[0].innerHTML, 'song1');
      });
  
  });
