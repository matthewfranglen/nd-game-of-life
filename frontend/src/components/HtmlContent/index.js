import React from 'react';
import PropTypes from 'prop-types';
import { Row } from 'react-bootstrap';

const HtmlContent = props => (
  <Row className={props.className} dangerouslySetInnerHTML={{ __html: props.html }} />
);

HtmlContent.propTypes = {
  className: PropTypes.string,
  html: PropTypes.string.isRequired,
};

export default HtmlContent;
