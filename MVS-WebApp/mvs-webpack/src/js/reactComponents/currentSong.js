'use strict';

class SkipButton extends React.Component {
    render() {
        return (
            <button>
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
                <SkipButton></SkipButton>
            </div>
        );
    }
}
