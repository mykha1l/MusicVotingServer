//sends data to back-end controller
export async function getAllSongs() {
    const response = await fetch("/api/v1/songs");
    const json = await response.json();

    return json;
}

export async function sendVotes(votes) {
    const response = await fetch('/api/v1/vote', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(votes)
    });
}

export async function getPairs() {
    const response = await fetch("/api/v1/pairs");
    const json = await response.json();

    return json;
}

export async function getMostlyVoted() {
    const response = await fetch("/api/v1/songs/mostlyVoted");
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error('Cannot get mostly voted songs. Status: ' +  response.status);
    }
}

export async function getUser() {
    const response = await fetch("/api/v1/user");
    const result = await response.json();

    return result;
}

export async function getCurrentSong() {
    const response = await fetch("/api/v1/songs/current");
    const text = await response.text();

    return text;
}

export async function skipSong() {
    await fetch("/api/v1/songs/current/stop");
}

export async function logout() {
    await fetch("/logout", {method: 'POST'});
}

export async function deleteSong(id) {
    const response = await fetch("/api/v1/songs/" + id, { method: 'DELETE' });
    const result = await response;

    return result;
}

export async function searchSong(input) {
    const response = await fetch("/api/v1/songs?search=" + encodeURIComponent(input));
    const result = await response.json();

    return result;
}

export async function updateSong(id, details) {
    const response = await fetch(`/api/v1/songs/${id}`, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(details)
    });
    const result = await response;

    return result;
}

export async function getVotersNumber() {
    const response = await fetch("/api/v1/get-voters-number");
    const data = await response.text();

    return data;
}

export async function getPlaylistSize() {
    const response = await fetch("/api/v1/get-playlist-size");
    const data = await response.text();

    return data;
}

export async function setVotersNumber(number) {
    const obj = { numb: number };
    await fetch('/api/v1/set-voters-number', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj),
    });
}

export async function setPlaylistSize(number) {
    const obj = { numb: number };
    await fetch('/api/v1/set-playlist-size', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj),
    });
}
