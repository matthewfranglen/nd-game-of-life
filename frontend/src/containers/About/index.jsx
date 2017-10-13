import React from 'react';
import { Grid } from 'react-bootstrap';
import HtmlContent from '../../components/HtmlContent';
import html from './index.md';

const AboutPage = () => (
  <Grid className="about">
    <HtmlContent className="about__text" html={html} />
  </Grid>
);

export default AboutPage;
