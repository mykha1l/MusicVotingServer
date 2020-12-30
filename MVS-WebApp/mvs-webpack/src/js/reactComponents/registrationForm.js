'use strict';

export class RegistrationForm extends React.Component {

    render() {
        return (
            <form action="api/v1/register" enctype="multipart/form-data" method="post">
                <div>
                    <label for="username">Username*:</label>
                    <input type="text" id="username" name="username"></input>
                    <label for="password">Password*:</label>
                    <input type="password" id="password" name="password"></input>
                    <label for="password2">Confirm password*:</label>
                    <input type="password" id="password2"></input>
                    <label for="age">Age:</label>
                    <input type="number" id="age" name="age"></input>
                    <label for="nationality">Nationality:</label>
                    <input type="text" id="nationality" name="nationality"></input>
                    <label for="genre">Favourite genre:</label>
                    <input type="text" id="genre" name="genre"></input>
                    <input type="submit" value="Submit"></input>
                </div>
            </form>
        )
    }
}
