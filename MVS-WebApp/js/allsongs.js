async function getAllSongs() {
    const response = await fetch("http://localhost:8080/api/v1/songs");
    const json = await response.json();

    return json;
}

function createSongList(data) {
    const songlist = document.getElementsByClassName("songlist")[0];
    for (const song of data) {
        const songNode = document.createElement("div");
        songNode.className = "song";
        songNode.innerHTML = song.rawName;
        songlist.appendChild(songNode);
    }
}

function init() {
    getAllSongs().then(data => createSongList(data))
        .catch(reason => console.log(reason.message));
}

document.onload = init();
