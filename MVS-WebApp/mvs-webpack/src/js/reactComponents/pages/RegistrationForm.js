import React from 'react';

export class RegistrationForm extends React.Component {

    render() {
        return (
            <form action="api/v1/register" encType="multipart/form-data" method="post">
                <div>
                    <label htmlFor="username">Username*:</label>
                    <input type="text" id="username" name="username"></input>
                    <label htmlFor="password">Password*:</label>
                    <input type="password" id="password" name="password"></input>
                    <label htmlFor="password2">Confirm password*:</label>
                    <input type="password" id="password2"></input>
                    <label htmlFor="age">Age:</label>
                    <input type="number" id="age" name="age"></input>
                    <label htmlFor="nationality">Nationality:</label>
                    <input type="text" id="nationality" name="nationality"></input>
                    <label htmlFor="genre">Favourite genre:</label>
                    <input type="text" id="genre" name="genre"></input>
                    <input type="submit" value="Submit"></input>
                </div>
            </form>
        )
    }
}
