import React, { PropTypes } from 'react';
import Header from '../Header';
import Footer from '../Footer';

const App = (props) => (
  <div>
    <Header />
    {props.children}
    <Footer />
  </div>
);

App.propTypes = {
  children: PropTypes.object.isRequired,
};

export default App;
