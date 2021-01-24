import { skipSong, getCurrentSong } from '../../api'

class SkipButton extends React.Component {

    render() {
        return (
            <button onClick={() => skipSong().then(() => this.props.reload())}>
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
        getCurrentSong()
            .then(data => this.setState({
                data: data,
            }));
    }

    componentDidMount() {
        getCurrentSong()
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
