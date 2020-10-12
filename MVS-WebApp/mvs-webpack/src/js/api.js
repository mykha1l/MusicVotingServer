export async function getAllSongs() {
    const response = await fetch("http://localhost:8080/api/v1/songs");
    const json = await response.json();

    return json;
}

export async function sendVotes(votes) {
    const response = await fetch('http://localhost:8080/api/v1/vote', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(votes)
    });
}

export async function getPairs() {
    const response = await fetch("http://localhost:8080/api/v1/pairs");
    const json = await response.json();

    return json;
}
