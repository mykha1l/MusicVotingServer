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
    if (!songName) {
        return;
    }
    parentContainer.innerHTML = '';
    let label = document.createElement('div');
    label.innerText = 'Currently playing:';
    let data = document.createElement('div');
    data.innerText = songName;
    let stopButton = document.createElement('button');
    stopButton.classList.add('stop-button');
    stopButton.innerText = 'Stop';
    stopButton.onclick = () => sendStopSongRequest();
    parentContainer.appendChild(label);
    parentContainer.appendChild(data);
    parentContainer.appendChild(stopButton);
}

function sendStopSongRequest() {
    const response = fetch("http://localhost:8080/api/v1/stop");
}
