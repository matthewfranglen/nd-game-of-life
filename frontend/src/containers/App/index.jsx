import React, { PropTypes } from 'react';
import Header from '../Header';
import Footer from '../Footer';

const App = ({ children }) => (
  <div>
    <Header />
    {children}
    <Footer />
  </div>
);

App.propTypes = {
  children: PropTypes.object.isRequired,
};

export default App;
