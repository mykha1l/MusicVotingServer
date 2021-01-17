export class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            errorMessages: [],
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        if (this.state.username && this.state.password) {
            const formData = new FormData();
            formData.append('username', this.state.username);
            formData.append('password', this.state.password);
            console.log('fetching');
            fetch("/login", { method: 'POST', body: formData }).then(() => window.location.reload());
        } else {
            let errorMsg = [];
            if (!this.state.username) {
                errorMsg.push('Username is empty.');
            }
            if (!this.state.password) {
                errorMsg.push('Password is empty.');
            }
            this.setState({ errorMessages: errorMsg });
        }
    }

    handleChange() {
        const name = event.target.name;
        const val = event.target.value;
        this.setState({ [name]: val, errorMessages: [] });
    }

    render() {
        const errors = this.state.errorMessages.map((msg) => <div id="error-validation" >{msg}</div>);
        return (
            <form onSubmit={this.handleSubmit}>
                {this.state.errorMessages && <React.Fragment>{errors}</React.Fragment>}
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" onChange={this.handleChange}></input>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" onChange={this.handleChange}></input>
                <input type="submit" value="Submit"></input>
            </form>
        )
    }
}
