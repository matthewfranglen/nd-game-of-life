const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: {
    app: [
      'webpack/hot/dev-server',
      'bootstrap-loader',
      path.resolve(__dirname, 'src/main.js'),
      path.resolve(__dirname, 'style/style.css'),
    ],
  },
  devtool: 'source-map',
  output: {
    pathinfo: true,
    path: path.resolve(__dirname, 'dist'),
    publicPath: './',
    filename: 'bundle.js',
  },
  watch: true,
  plugins: [
    new HtmlWebpackPlugin({ title: 'N Dimensional Game of Life' }),
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
    }),
  ],
  module: {
    loaders: [
      {
        test: /\.(js|jsx)$/,
        loader: 'babel-loader',
        include: [
          path.resolve(__dirname, 'src'),
        ],
      },
      { test: /\.css$/, loaders: ['style-loader', 'css-loader'] },
      { test: /\.json/, loader: 'json-loader' },
      { test: /\.md/, loader: ['html-loader', 'markdown-loader'] },
      { test: /\.(png|jpg|jpeg|gif|svg|woff|woff2)$/, loader: 'url-loader?limit=10000' },
      { test: /\.(eot|ttf|wav|mp3)$/, loader: 'file-loader' },
    ],
  },
};
