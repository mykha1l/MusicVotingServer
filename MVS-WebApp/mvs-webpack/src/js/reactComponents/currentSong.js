'use strict';

class SkipButton extends React.Component {

    render() {
        return (
            <button onClick={() => fetch("http://localhost:8080/api/v1/stop").then(() => this.props.reload())}>
                Skip
            </button>
        )
    }
}

export class CurrentSong extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: null,
        };
    }

    reload() {
        fetch('http://localhost:8080/api/v1/currentSong')
            .then(response => response.text())
            .then(data => this.setState({
                data: data,
            }));
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/v1/currentSong')
            .then(response => response.text())
            .then(data => this.setState({ data }));
    }

    render() {
        const { data } = this.state;
        return (
            <div>
                <div>{data}</div>
                <SkipButton reload={this.reload.bind(this)}></SkipButton>
            </div>
        );
    }
}
