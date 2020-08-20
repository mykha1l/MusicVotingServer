var songPairs = [];
var votes = [];

async function getPairs() {
    const response = await fetch("http://localhost:8080/api/v1/pairs");
    const json = await response.json();

    return json;
}

async function sendVotes(votes) {
    const response = await fetch('http://localhost:8080/api/v1/vote', {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(votes)
      });
}

function displayPair(index, songListContainer) {
    clearSongListContainer(songListContainer);
    const pair = songPairs[index];
    if (!pair || !pair[0]) {
        sendVotes(votes);
        return;
    }
    createSongNode(pair[0], songListContainer, () => {
        votes.push(pair[0].filename);
        displayPair(index + 1, songListContainer);
    });
    if (!pair[1]) {
        return;
    }
    createSongNode(pair[1], songListContainer, () => {
        votes.push(pair[1].filename);
        displayPair(index + 1, songListContainer);
    });
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
