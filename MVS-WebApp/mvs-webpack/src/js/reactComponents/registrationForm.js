'use strict';

export class RegistrationForm extends React.Component {

    render() {
        return (
            <form>
                <label for="uname">Username:</label>
                <input type="text" id="uname" name="uname"></input>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password"></input>
                <input type="submit" value="Submit"></input>
            </form>
        )
    }
}
