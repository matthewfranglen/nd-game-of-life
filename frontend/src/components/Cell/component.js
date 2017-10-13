import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'react-bootstrap';

const toggleOnClick = (x, y, isOn, turnCellOn, turnCellOff) => {
  if (isOn) {
    return () => turnCellOff(x, y);
  }
  return () => turnCellOn(x, y);
};

const Cell = ({ x, y, isOn, turnCellOn, turnCellOff }) => (
  <Button
    id={`cell--${x}:${y}`}
    className={isOn ? 'cell--on' : 'cell--off'}
    title={isOn ? 'On' : 'Off'}
    onClick={toggleOnClick(x, y, isOn, turnCellOn, turnCellOff)}
  />
);

Cell.propTypes = {
  x: PropTypes.number.isRequired,
  y: PropTypes.number.isRequired,
  isOn: PropTypes.bool.isRequired,
  turnCellOn: PropTypes.func.isRequired,
  turnCellOff: PropTypes.func.isRequired,
};

export default Cell;
