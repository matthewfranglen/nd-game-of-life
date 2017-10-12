import React from 'react';
import { Grid } from 'react-bootstrap';

import HtmlContent from '../../components/HtmlContent';
import GameDisplay from '../../components/GameDisplay';
import html from './index.md';

const HomePage = () => (
  <Grid className="home">
    <HtmlContent className="home__intro" html={html} />
    <GameDisplay className="home__display" />
  </Grid>
);

export default HomePage;
