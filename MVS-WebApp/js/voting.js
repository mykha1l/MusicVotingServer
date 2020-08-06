var songPairs = [];

async function getPairs() {
    const response = await fetch("http://localhost:8080/api/v1/pairs");
    const json = await response.json();

    return json;
}

function displayPair(index, songListContainer) {
    clearSongListContainer(songListContainer);
    const pair = songPairs[index];
    if (!pair) {
        return;
    }
    createSongNode(pair[0], songListContainer, () => displayPair(index + 1, songListContainer));
    if (!pair[1]) {
        return;
    }
    createSongNode(pair[1], songListContainer, () => displayPair(index + 1, songListContainer));
}

function initVoting() {
    const songListContainer = findSongListContainer();
    if (!songListContainer) {
        renderErrorMsg("Cannot find songListContainer");
        return;
    }
    getPairs().then(data => {
        songPairs = data;
        displayPair(0, songListContainer)
    })
        .catch(reason => renderErrorMsg(reason.message));
}
