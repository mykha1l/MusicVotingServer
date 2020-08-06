async function getAllSongs() {
    const response = await fetch("http://localhost:8080/api/v1/songs");
    const json = await response.json();

    return json;
}

function findSongListContainer() {
    return document.getElementsByClassName("songlist")[0];
}

function clearSongListContainer(songListContainer) {
    songListContainer.innerHTML = "";
}

function createSongNodeElement(className, data, parent, onclick) {
    const el = document.createElement("div");
    el.className = className;
    if (data) {
        el.innerHTML = data;
    }
    if (onclick) {
        el.onclick = onclick;
    }
    parent.appendChild(el);

    return el;
}

function createSongNode(song, songListContainer, onclick) {
    const songEl = createSongNodeElement("song", null, songListContainer, onclick);
    createSongNodeElement("artist", song.artist, songEl );
    createSongNodeElement("song-name", song.title ? song.title : song.filename, songEl );
}

function createSongList(data, songListContainer) {
    clearSongListContainer(songListContainer);
    for (const song of data) {
        createSongNode(song, songListContainer)
    }
}

function renderErrorMsg(message) {
    const errorNode = document.createElement("div");
    errorNode.className = "error";
    errorNode.innerHTML = message;
    document.getElementsByTagName("body")[0].appendChild(errorNode);
}

function initAllSongs() {
    const songListContainer = findSongListContainer();
    if (!songListContainer) {
        renderErrorMsg("Cannot find songListContainer");
        return;
    }
    getAllSongs().then(data => createSongList(data, songListContainer))
        .catch(reason => renderErrorMsg(reason.message));
}
