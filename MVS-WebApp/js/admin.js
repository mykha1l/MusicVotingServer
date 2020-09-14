async function getCurrentSong() {
    const response = await fetch("http://localhost:8080/api/v1/currentSong");
    const text = await response.text();

    return text;
}

function displayCurrentSong() {
    const songListContainer = findSongListContainer();
    getCurrentSong().then(data => createCurrentSongContainer(data, songListContainer))
        .catch(reason => renderErrorMsg(reason.message));
}

function createCurrentSongContainer(songName, parentContainer) {
    parentContainer.innerHTML = '';
    let label = document.createElement('div');
    label.innerText = 'Currently playing:';
    let data = document.createElement('div');
    data.innerText = songName;
    parentContainer.appendChild(label);
    parentContainer.appendChild(data);
}
