const path = require("path")
const webpack = require("webpack")

module.exports = {
    // entry: './public/dist/app.js',
    entry: './src/main/resources/static/js/app.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'src/main/resources/static/dist')
    },
    module: {
        rules: [
            {
                test: /\.m?js$/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            }
        ]
    }
}