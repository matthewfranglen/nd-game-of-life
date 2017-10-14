/* eslint import/no-extraneous-dependencies: 0 */

const webpack = require('webpack');
const base = require('./webpack.base');

module.exports = Object.assign({}, base, {
  plugins: base.plugins.concat([
    new webpack.optimize.UglifyJsPlugin(),
  ]),
});
