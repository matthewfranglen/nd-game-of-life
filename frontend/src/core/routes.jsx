import React from 'react';
import { Router, IndexRoute, Route, browserHistory } from 'react-router';

import App from '../containers/App';
import Home from '../containers/Home';
import About from '../containers/About';

const Routes = () => (
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={Home} />
      <Route path="/about" component={About} />
    </Route>
  </Router>
);

export default Routes;
