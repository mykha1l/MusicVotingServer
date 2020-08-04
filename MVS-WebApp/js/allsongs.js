async function getAllSongs() {
    const response = await fetch("http://localhost:8080/api/v1/songs");
    const json = await response.json();

    return json;
}

function findSongListContainer() {
    return document.getElementsByClassName("songlist")[0];
}

function createSongList(data, songListContainer) {
    for (const song of data) {
        const songNode = document.createElement("div");
        songNode.className = "song";
        songNode.innerHTML = song.title ? song.title : song.filename;
        songListContainer.appendChild(songNode);
    }
}

function renderErrorMsg(message) {
    const errorNode = document.createElement("div");
    errorNode.className = "error";
    errorNode.innerHTML = message;
    document.getElementsByTagName("body")[0].appendChild(errorNode);
}

function init() {
    const songListContainer = findSongListContainer();
    if (!songListContainer) {
        renderErrorMsg("Cannot find songListContainer");
        return;
    }
    getAllSongs().then(data => createSongList(data, songListContainer))
        .catch(reason => renderErrorMsg(reason.message));
}
