export class LoginForm extends React.Component {

    render() {
        return (
            <form action="/login" enctype="multipart/form-data" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username"></input>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password"></input>
                <input type="submit" value="Submit"></input>
            </form>
        )
    }
}
