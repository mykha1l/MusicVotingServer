const HtmlWebPackPlugin = require("html-webpack-plugin");
const path = require('path');

module.exports = {
    entry: {
        user: './src/js/reactComponents/user.js',
        admin: './src/js/reactComponents/admin.js'
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "[name].js"
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            },
            {
                test: /\.html$/,
                use: [
                    {
                        loader: "html-loader"
                    }
                ]
            }
        ]
    }
};
