# MusicVotingServer
Bachelor Project

Structure:
- mvs-spring - contains backend. Written in Java with Spring Boot framework, using Maven for building.
- MVS-WebApp - contains frontend. Written in JavaScript ES6 with React framework, using Webpack for bundling. In addition this directory contains a directory with mp3 song files.
- mvs_flutter - mobile native client made with Flutter. Supports both Android and iOs.

<img src="rasp.jpg" alt="Raspberry Pi photo" border="0" width="300"></a>

Music Voting Server - is an application that generates playlists according to collected votes from users and plays music. It runs in a local wi-fi network and can be installed on Raspberry Pi (or any other computer).

Server side is written in Java using Spring Boot framework. On the client side there are React web app and mobile flutter client app available.

<img src="https://www.mkwd.net/wp-content/uploads/2019/11/ES6.jpg" alt="ES6" width="60"><img src="https://onextrapixel.com/wp-content/uploads/2016/04/reactjs-thumb.jpg" alt="ReactJs" width="60"><img src="https://www.dariawan.com/media/images/tech-spring-boot.width-1024.png" alt="Spring Boot" width="100">
<img src="https://miro.medium.com/max/700/1*TkNd1PwwwdBi9Z3kdG5Hng.png" alt="Flutter" width="60"><img src="https://upload.wikimedia.org/wikipedia/commons/3/38/SQLite370.svg" alt="sqlite" width="70">

<img src="https://i.ibb.co/x200Rsy/Blank-Diagram.png" alt="Blank-Diagram" border="0" width="1000"></a>


| Http method | Endpoint                       | Description                    |
| ----------- | ------------------------------ | -------------------------------|
| GET         | api/v1/songs                   | Returns a list of all songs |
| GET         | api/v1/pairs                   | Returns a list of songs pairs (for voting) |
| POST        | api/v1/vote                    | Accepts list of songs chosen by a user |
| POST        | api/v1/songs/upload            | Accepts mp3 song file |
| GET         | api/v1/songs/current           | Returns currently played song |
| GET         | api/v1/songs/mostlyVoted       | Returns a list of mostly voted songs |
| GET         | api/v1/songs/current/stop      | Stops playing current song |
| DELETE      | api/v1/songs/{id}              | Deletes a song |
| PUT         | api/v1/songs/{id}              | Changes song info |
| GET         | api/v1/get-voters-number       | Returns voters number setting |
| POST        | api/v1/songs/set-voters-number | Changes voters number setting |
| GET         | api/v1/songs/get-playlist-size | Returns playlist size setting |
| POST        | api/v1/songs/set-playlist-size | Changes playlist size setting |
| GET         | api/v1/user                    | Returns user info |
| POST        | api/v1/register                | Registers a new user |
