'use strict';

export class SongUpload extends React.Component {

    upload(input) {
        const formData = new FormData();
        formData.append('file', input.target.files[0]);
        fetch('http://mvs.local:8080/api/v1/upload', {
            method: 'POST',
            body: formData
        }).then(
            response => response.json()
        ).then(
            success => console.log(success)
        ).catch(
            error => console.log("Song upload error: " + error)
        );
    }

    render() {
        return (
            <div>You can upload an mp3 file to the server:
                <div><input type="file" id="input" onChange={this.upload}></input></div>
            </div>
        )
    }

}
